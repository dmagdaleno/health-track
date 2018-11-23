package br.com.healthtech.healthtrack.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.healthtech.healthtrack.dao.AtividadeFisicaDAO;
import br.com.healthtech.healthtrack.db.ConnectionManager;
import br.com.healthtech.healthtrack.exception.DBException;
import br.com.healthtech.healthtrack.modelo.Usuario;
import br.com.healthtech.healthtrack.modelo.registro.AtividadeFisica;
import br.com.healthtech.healthtrack.modelo.registro.Tipo;
import br.com.healthtech.healthtrack.utils.DateUtil;

/**
 * Classe responsável por abstrair a comunicação com o banco de dados Oracle
 * da entidade {@link AtividadeFisica}
 * 
 * @author dmagdaleno
 *
 */
public class AtividadeFisicaDAOOracle implements AtividadeFisicaDAO {
	
	private ConnectionManager manager;
	
	public AtividadeFisicaDAOOracle() {
		manager = ConnectionManager.getInstance();
	}
	
	@Override
	public void insere(AtividadeFisica registro) throws DBException {
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO T_HTK_ATV_FISICA (");
		builder.append(" id_atv_fisica,"); 
		builder.append(" fk_id_usuario,"); 
		builder.append(" fk_id_tp_atv_fisica,"); 
		builder.append(" vl_caloria,"); 
		builder.append(" ds_atv_fisica,"); 
		builder.append(" dt_atv_fisica) "); 
		builder.append("VALUES (SQ_TB_ATV_FISICA.NEXTVAL, ?, ?, ?, ?, TO_DATE(?,'YYYY-MM-DD\"T\"HH24:MI:SS'))");
		String insert = builder.toString();
		
		try(
			Connection conexao = manager.obterConexao();
			PreparedStatement stmt = conexao.prepareStatement(insert);
		) {
			stmt.setLong(1, registro.getUsuario().getId());
			stmt.setInt(2, registro.getTipo().getId());
			stmt.setDouble(3, registro.getGastoCalorico().doubleValue());
			stmt.setString(4, registro.getDescricao());
			stmt.setString(5, DateUtil.toText(registro.getDataRegistro()));
			stmt.executeUpdate();
		} 
		catch (SQLIntegrityConstraintViolationException e) {
			String msg = String.format("Chave primária [%d] ou estranjeira [%d] inválida", 
					registro.getId(), registro.getUsuario().getId());
			System.out.println(msg);
			e.printStackTrace();
			throw new DBException(e);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new DBException(e);
		}
	}

	@Override
	public void insereTodos(List<AtividadeFisica> registros) throws DBException {
		registros.forEach(registro -> {
			try {
				this.insere(registro);
			} catch (DBException e) {
				System.out.println("Não foi possível inserir: " + registro);
			}
		});
	}

	@Override
	public AtividadeFisica buscaPor(Long id) throws DBException {
		StringBuilder query = new StringBuilder();
		query.append("SELECT T.*, TO_CHAR(T.dt_atv_fisica, 'YYYY-MM-DD\"T\"HH24:MI:SS') AS dt_text, ");
		query.append(" TP.id_tp_atv_fisica AS tp_id, TP.ds_tp_atv_fisica AS tp_descricao ");
		query.append("FROM T_HTK_ATV_FISICA T, T_HTK_TP_ATV_FISICA TP ");
		query.append("WHERE T.id_atv_fisica = ? AND T.fk_id_tp_atv_fisica = TP.id_tp_atv_fisica");
		
		AtividadeFisica registro = null;
		try(
			Connection conexao = manager.obterConexao();
			PreparedStatement stmt = conexao.prepareStatement(query.toString());
		) {
			stmt.setLong(1, id);
			
			try(ResultSet rs = stmt.executeQuery()) {
				if(rs.next()) {
					Usuario usuario = new Usuario(rs.getLong("fk_id_usuario"));
					BigDecimal calorias = new BigDecimal(rs.getDouble("vl_caloria"));
					String descricao = rs.getString("ds_atv_fisica");
					LocalDateTime dataRegistro = DateUtil.toDateTime(rs.getString("dt_text"));
					
					int tipoId = rs.getInt("tp_id");
					String tipoDesc = rs.getString("tp_descricao");
					Tipo tipo = new Tipo(tipoId, tipoDesc);
					
					registro = new AtividadeFisica(id, tipo, descricao, calorias, dataRegistro, usuario);
				}
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
			throw new DBException(e);
		}
		
		return registro;
	}

	@Override
	public List<AtividadeFisica> buscaPor(Usuario usuario) throws DBException {
		StringBuilder query = new StringBuilder();
		query.append("SELECT T.*, TO_CHAR(T.dt_atv_fisica, 'YYYY-MM-DD\"T\"HH24:MI:SS') AS dt_text, ");
		query.append(" TP.id_tp_atv_fisica AS tp_id, TP.ds_tp_atv_fisica AS tp_descricao ");
		query.append("FROM T_HTK_ATV_FISICA T, T_HTK_TP_ATV_FISICA TP ");
		query.append("WHERE T.fk_id_usuario = ? AND T.fk_id_tp_atv_fisica = TP.id_tp_atv_fisica ");
		query.append("ORDER BY T.dt_atv_fisica DESC");
		
		List<AtividadeFisica> registros = new ArrayList<>();
		try(
			Connection conexao = manager.obterConexao();
			PreparedStatement stmt = conexao.prepareStatement(query.toString());
		) {
			stmt.setLong(1, usuario.getId());

			try(ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					Long id = rs.getLong("id_atv_fisica");
					BigDecimal calorias = new BigDecimal(rs.getDouble("vl_caloria"));
					String descricao = rs.getString("ds_atv_fisica");
					LocalDateTime dataRegistro = DateUtil.toDateTime(rs.getString("dt_text"));
					
					int tipoId = rs.getInt("tp_id");
					String tipoDesc = rs.getString("tp_descricao");
					Tipo tipo = new Tipo(tipoId, tipoDesc);
					
					AtividadeFisica registro = 
							new AtividadeFisica(id, tipo, descricao, calorias, dataRegistro, usuario);
					
					registros.add(registro);
				}
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(e);
		}
		
		return registros;
	}

	@Override
	public List<AtividadeFisica> buscaPor(Usuario usuario, int quantidade) throws DBException {
		StringBuilder query = new StringBuilder();
		query.append("SELECT * FROM ( ");
		query.append(" SELECT T.*, TO_CHAR(T.dt_atv_fisica, 'YYYY-MM-DD\"T\"HH24:MI:SS') AS dt_text, ");
		query.append("  TP.id_tp_atv_fisica AS tp_id, TP.ds_tp_atv_fisica AS tp_descricao ");
		query.append(" FROM T_HTK_ATV_FISICA T, T_HTK_TP_ATV_FISICA TP ");
		query.append(" WHERE T.fk_id_usuario = ? AND T.fk_id_tp_atv_fisica = TP.id_tp_atv_fisica ");
		query.append(" ORDER BY T.dt_atv_fisica DESC ) ");
		query.append("WHERE ROWNUM <= ?");
		
		List<AtividadeFisica> registros = new ArrayList<>();
		try(
			Connection conexao = manager.obterConexao();
			PreparedStatement stmt = conexao.prepareStatement(query.toString());
		) {
			stmt.setLong(1, usuario.getId());
			stmt.setLong(2, quantidade);

			try(ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					Long id = rs.getLong("id_atv_fisica");
					BigDecimal calorias = new BigDecimal(rs.getDouble("vl_caloria"));
					String descricao = rs.getString("ds_atv_fisica");
					LocalDateTime dataRegistro = DateUtil.toDateTime(rs.getString("dt_text"));
					
					int tipoId = rs.getInt("tp_id");
					String tipoDesc = rs.getString("tp_descricao");
					Tipo tipo = new Tipo(tipoId, tipoDesc);
					
					AtividadeFisica registro = 
							new AtividadeFisica(id, tipo, descricao, calorias, dataRegistro, usuario);
					
					registros.add(registro);
				}
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(e);
		}
		
		return registros;
	}

	@Override
	public List<AtividadeFisica> buscaTodos() {
		List<AtividadeFisica> registros = new ArrayList<>();
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT T.*, TO_CHAR(T.dt_atv_fisica, 'YYYY-MM-DD\"T\"HH24:MI:SS') AS dt_text, ");
		builder.append(" TP.id_tp_atv_fisica AS tp_id, TP.ds_tp_atv_fisica AS tp_descricao ");
		builder.append("FROM T_HTK_ATV_FISICA T, T_HTK_TP_ATV_FISICA TP ");
		builder.append("WHERE T.fk_id_tp_atv_fisica = TP.id_tp_atv_fisica ");
		builder.append("ORDER BY T.dt_atv_fisica DESC");
		String query = builder.toString();
		
		try (
			Connection conexao = manager.obterConexao();
			PreparedStatement stmt = conexao.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
		){
			while (rs.next()) {
				Long id = rs.getLong("id_atv_fisica");
				Usuario usuario = new Usuario(rs.getLong("fk_id_usuario"));
				BigDecimal calorias = new BigDecimal(rs.getDouble("vl_caloria"));
				String descricao = rs.getString("ds_atv_fisica");
				LocalDateTime dataRegistro = DateUtil.toDateTime(rs.getString("dt_text"));
				
				int tipoId = rs.getInt("tp_id");
				String tipoDesc = rs.getString("tp_descricao");
				Tipo tipo = new Tipo(tipoId, tipoDesc);
				
				AtividadeFisica registro = new AtividadeFisica(id, tipo, descricao, calorias, dataRegistro, usuario);
				
				registros.add(registro);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return Collections.unmodifiableList(registros);
	}

	@Override
	public void atualiza(AtividadeFisica registro) throws DBException {
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE T_HTK_ATV_FISICA T SET");
		builder.append(" T.fk_id_tp_atv_fisica = ?,"); 
		builder.append(" T.vl_caloria = ?,"); 
		builder.append(" T.ds_atv_fisica = ?,"); 
		builder.append(" T.dt_atv_fisica = TO_DATE(?,'YYYY-MM-DD\"T\"HH24:MI:SS') "); 
		builder.append("WHERE T.id_atv_fisica = ? AND T.fk_id_usuario = ?");
		String atualizar = builder.toString();
		
		try(
			Connection conexao = manager.obterConexao();
			PreparedStatement stmt = conexao.prepareStatement(atualizar);
		) {
			stmt.setInt(1, registro.getTipo().getId());
			stmt.setDouble(2, registro.getGastoCalorico().doubleValue());
			stmt.setString(3, registro.getDescricao());
			stmt.setString(4, DateUtil.toText(registro.getDataRegistro()));
			stmt.setLong(5, registro.getId());
			stmt.setLong(6, registro.getUsuario().getId());
			stmt.executeUpdate();
		} 
		catch (SQLIntegrityConstraintViolationException e) {
			String msg = String.format("Chave primária [%d] ou estranjeira [%d] inválida", 
					registro.getId(), registro.getUsuario().getId());
			System.out.println(msg);
			e.printStackTrace();
			throw new DBException(e);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new DBException(e);
		}
	}

	@Override
	public void exclui(Long id) throws DBException {
		String delete = "DELETE FROM T_HTK_ATV_FISICA T WHERE T.id_atv_fisica = ?";
		
		try(
			Connection conexao = manager.obterConexao();
			PreparedStatement stmt = conexao.prepareStatement(delete);
		) {
			stmt.setLong(1, id);
			stmt.executeUpdate();
		} 
		catch (Exception e) {
			e.printStackTrace();
			throw new DBException(e);
		}
	}

	@Override
	public void excluiTodos() throws DBException {
		String delete = "DELETE FROM T_HTK_ATV_FISICA";
		
		try(
			Connection conexao = manager.obterConexao();
			PreparedStatement stmt = conexao.prepareStatement(delete);
		) {
			stmt.executeUpdate();
		} 
		catch (Exception e) {
			e.printStackTrace();
			throw new DBException(e);
		}
	}
}
