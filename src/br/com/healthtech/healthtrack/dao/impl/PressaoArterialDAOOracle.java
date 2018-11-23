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

import br.com.healthtech.healthtrack.dao.PressaoArterialDAO;
import br.com.healthtech.healthtrack.db.ConnectionManager;
import br.com.healthtech.healthtrack.exception.DBException;
import br.com.healthtech.healthtrack.modelo.Usuario;
import br.com.healthtech.healthtrack.modelo.registro.PressaoArterial;
import br.com.healthtech.healthtrack.utils.DateUtil;

/**
 * Classe responsável por abstrair a comunicação com o banco de dados Oracle
 * da entidade {@link PressaoArterial}
 * 
 * @author dmagdaleno
 *
 */
public class PressaoArterialDAOOracle implements PressaoArterialDAO {
	
	private Connection conexao;
	
	public PressaoArterialDAOOracle() {
		ConnectionManager manager = ConnectionManager.getInstance();
		conexao = manager.obterConexao();
	}
	
	@Override
	public void insere(PressaoArterial registro) throws DBException {
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO T_HTK_PRESSAO (");
		builder.append(" id_pressao,"); 
		builder.append(" fk_id_usuario,"); 
		builder.append(" vl_pressao_max,"); 
		builder.append(" vl_pressao_min,"); 
		builder.append(" dt_medida) "); 
		builder.append("VALUES (SQ_TB_PRESSAO.NEXTVAL, ?, ?, ?, TO_DATE(?,'YYYY-MM-DD\"T\"HH24:MI:SS'))");
		String insert = builder.toString();
		
		try(PreparedStatement stmt = conexao.prepareStatement(insert)) {
			stmt.setLong(1, registro.getUsuario().getId());
			stmt.setDouble(2, registro.getPressaoMaxima().doubleValue());
			stmt.setDouble(3, registro.getPressaoMinima().doubleValue());
			stmt.setString(4, DateUtil.toText(registro.getDataRegistro()));
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
	public void insereTodos(List<PressaoArterial> registros) {
		registros.forEach(registro -> {
			try {
				this.insere(registro);
			} catch (DBException e) {
				System.out.println("Não foi possível inserir: " + registro);
			}
		});
	}

	@Override
	public PressaoArterial buscaPor(Long id) throws DBException {
		StringBuilder query = new StringBuilder();
		query.append("SELECT T.*, TO_CHAR(T.dt_medida, 'YYYY-MM-DD\"T\"HH24:MI:SS') AS dt_text ");
		query.append("FROM T_HTK_PRESSAO T ");
		query.append("WHERE T.id_pressao = ?");
		
		PressaoArterial registro = null;
		try(PreparedStatement stmt = conexao.prepareStatement(query.toString())) {
			stmt.setLong(1, id);
			
			try(ResultSet rs = stmt.executeQuery()) {
				if(rs.next()) {
					Usuario usuario = new Usuario(rs.getLong("fk_id_usuario"));
					BigDecimal pressaoMax = new BigDecimal(rs.getDouble("vl_pressao_max"));
					BigDecimal pressaoMin = new BigDecimal(rs.getDouble("vl_pressao_min"));
					LocalDateTime dataRegistro = DateUtil.toDateTime(rs.getString("dt_text"));
					
					registro = new PressaoArterial(id, pressaoMax, pressaoMin, dataRegistro, usuario);
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
	public List<PressaoArterial> buscaPor(Usuario usuario) throws DBException {
		StringBuilder query = new StringBuilder();
		query.append("SELECT T.*, TO_CHAR(T.dt_medida, 'YYYY-MM-DD\"T\"HH24:MI:SS') AS dt_text ");
		query.append("FROM T_HTK_PRESSAO T ");
		query.append("WHERE T.fk_id_usuario = ? ");
		query.append("ORDER BY T.dt_medida DESC");
		
		List<PressaoArterial> registros = new ArrayList<>();
		try(PreparedStatement stmt = conexao.prepareStatement(query.toString())) {
			stmt.setLong(1, usuario.getId());

			try(ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					Long id = rs.getLong("id_pressao");
					BigDecimal pressaoMax = new BigDecimal(rs.getDouble("vl_pressao_max"));
					BigDecimal pressaoMin = new BigDecimal(rs.getDouble("vl_pressao_min"));
					LocalDateTime dataRegistro = DateUtil.toDateTime(rs.getString("dt_text"));
					
					PressaoArterial registro = new PressaoArterial(id, pressaoMax, pressaoMin, dataRegistro, usuario);
					
					registros.add(registro);
				}
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
			throw new DBException(e);
		}
		
		return registros;
	}

	@Override
	public List<PressaoArterial> buscaPor(Usuario usuario, int quantidade) throws DBException {
		StringBuilder query = new StringBuilder();
		query.append("SELECT * FROM ( ");
		query.append(" SELECT T.*, TO_CHAR(T.dt_medida, 'YYYY-MM-DD\"T\"HH24:MI:SS') AS dt_text ");
		query.append(" FROM T_HTK_PRESSAO T ");
		query.append(" WHERE T.fk_id_usuario = ? ");
		query.append(" ORDER BY T.dt_medida DESC ) ");
		query.append("WHERE ROWNUM <= ?");
		
		List<PressaoArterial> registros = new ArrayList<>();
		try(PreparedStatement stmt = conexao.prepareStatement(query.toString())) {
			stmt.setLong(1, usuario.getId());
			stmt.setLong(2, quantidade);

			try(ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					Long id = rs.getLong("id_pressao");
					BigDecimal pressaoMax = new BigDecimal(rs.getDouble("vl_pressao_max"));
					BigDecimal pressaoMin = new BigDecimal(rs.getDouble("vl_pressao_min"));
					LocalDateTime dataRegistro = DateUtil.toDateTime(rs.getString("dt_text"));
					
					PressaoArterial registro = new PressaoArterial(id, pressaoMax, pressaoMin, dataRegistro, usuario);
					
					registros.add(registro);
				}
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
			throw new DBException(e);
		}
		
		return registros;
	}

	@Override
	public List<PressaoArterial> buscaTodos() {
		List<PressaoArterial> registros = new ArrayList<>();
		String query = "SELECT T.*, TO_CHAR(T.dt_medida, 'YYYY-MM-DD\"T\"HH24:MI:SS') AS dt_text FROM T_HTK_PRESSAO P";
		
		try (
			PreparedStatement stmt = conexao.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
		){
			while (rs.next()) {
				Long id = rs.getLong("id_pressao");
				Usuario usuario = new Usuario(rs.getLong("fk_id_usuario"));
				BigDecimal pressaoMax = new BigDecimal(rs.getDouble("vl_pressao_max"));
				BigDecimal pressaoMin = new BigDecimal(rs.getDouble("vl_pressao_min"));
				LocalDateTime dataRegistro = DateUtil.toDateTime(rs.getString("dt_text"));
				
				PressaoArterial registro = new PressaoArterial(id, pressaoMax, pressaoMin, dataRegistro, usuario);
				
				registros.add(registro);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return Collections.unmodifiableList(registros);
	}

	@Override
	public void atualiza(PressaoArterial registro) throws DBException {
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE T_HTK_PRESSAO T SET");
		builder.append(" T.vl_pressao_max = ?,"); 
		builder.append(" T.vl_pressao_min = ?,"); 
		builder.append(" T.dt_medida = TO_DATE(?,'YYYY-MM-DD\"T\"HH24:MI:SS') "); 
		builder.append("WHERE T.id_pressao = ? AND T.fk_id_usuario = ?");
		String atualizar = builder.toString();
		
		try(PreparedStatement stmt = conexao.prepareStatement(atualizar)) {
			stmt.setDouble(1, registro.getPressaoMaxima().doubleValue());
			stmt.setDouble(2, registro.getPressaoMinima().doubleValue());
			stmt.setString(3, DateUtil.toText(registro.getDataRegistro()));
			stmt.setLong(4, registro.getId());
			stmt.setLong(5, registro.getUsuario().getId());
			stmt.executeUpdate();
		} 
		catch (SQLIntegrityConstraintViolationException e) {
			String msg = String.format("Chave primária [%d] ou estranjeira [%d] inválida", 
					registro.getId(), registro.getUsuario().getId());
			System.out.println(msg);
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new DBException(e);
		}
	}
	
	@Override
	public void exclui(Long id) throws DBException {
		String delete = "DELETE FROM T_HTK_PRESSAO T WHERE T.id_pressao = ?";
		
		try(PreparedStatement stmt = conexao.prepareStatement(delete)) {
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
		String delete = "DELETE FROM T_HTK_PRESSAO";
		
		try(PreparedStatement stmt = conexao.prepareStatement(delete)) {
			stmt.executeUpdate();
		}  
		catch (Exception e) {
			e.printStackTrace();
			throw new DBException(e);
		} 
	}
	
	public void fechaConexao() {
		try {
			this.conexao.close();
		} catch (SQLException e) {
			System.out.println("Erro ao fechar conexão.");
			e.printStackTrace();
		}
	}
}