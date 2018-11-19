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

import br.com.healthtech.healthtrack.dao.PesoDAO;
import br.com.healthtech.healthtrack.db.ConnectionManager;
import br.com.healthtech.healthtrack.exception.DBException;
import br.com.healthtech.healthtrack.modelo.Usuario;
import br.com.healthtech.healthtrack.modelo.registro.Peso;
import br.com.healthtech.healthtrack.utils.DateUtil;

/**
 * Classe responsável por abstrair a comunicação com o banco de dados Oracle
 * da entidade {@link Peso}
 * 
 * @author dmagdaleno
 *
 */
public class PesoDAOOracle implements PesoDAO {
	
	private Connection conexao;
	
	public PesoDAOOracle() {
		ConnectionManager manager = ConnectionManager.getInstance();
		conexao = manager.obterConexao();
	}
	
	@Override
	public void insere(Peso registro) throws DBException {
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO T_HTK_PESO (");
		builder.append(" id_peso,"); 
		builder.append(" fk_id_usuario,"); 
		builder.append(" vl_peso,"); 
		builder.append(" dt_medida) "); 
		builder.append("VALUES (SQ_TB_PESO.NEXTVAL, ?, ?, TO_DATE(?,'YYYY-MM-DD\"T\"HH24:MI:SS'))");
		String insert = builder.toString();
		
		try(PreparedStatement stmt = conexao.prepareStatement(insert)) {
			stmt.setLong(1, registro.getUsuario().getId());
			stmt.setDouble(2, registro.getPeso().doubleValue());
			stmt.setString(3, DateUtil.toText(registro.getDataRegistro()));
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
	public void insereTodos(List<Peso> pesos) throws DBException {
		pesos.forEach(peso -> {
			try {
				this.insere(peso);
			} catch (DBException e) {
				System.out.println("Não foi possível inserir: " + peso);
			}
		});
	}
	
	@Override
	public Peso buscaPor(Long id) throws DBException {
		StringBuilder query = new StringBuilder();
		query.append("SELECT P.*, TO_CHAR(P.dt_medida, 'YYYY-MM-DD\"T\"HH24:MI:SS') AS dt_text ");
		query.append("FROM T_HTK_PESO P ");
		query.append("WHERE P.id_peso = ?");
		
		Peso registro = null;
		try(PreparedStatement stmt = conexao.prepareStatement(query.toString())) {
			stmt.setLong(1, id);
			
			try(ResultSet rs = stmt.executeQuery()) {
				if(rs.next()) {
					Usuario usuario = new Usuario(rs.getLong("fk_id_usuario"));
					BigDecimal peso = new BigDecimal(rs.getDouble("vl_peso"));
					LocalDateTime dataRegistro = DateUtil.toDateTime(rs.getString("dt_text"));
					
					registro = new Peso(id, peso, dataRegistro, usuario);
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
	public List<Peso> buscaPor(Usuario usuario) throws DBException {
		StringBuilder query = new StringBuilder();
		query.append("SELECT P.*, TO_CHAR(P.dt_medida, 'YYYY-MM-DD\"T\"HH24:MI:SS') AS dt_text ");
		query.append("FROM T_HTK_PESO P ");
		query.append("WHERE P.fk_id_usuario = ? ");
		query.append("ORDER BY P.dt_medida DESC");
		
		List<Peso> registros = new ArrayList<>();
		try(PreparedStatement stmt = conexao.prepareStatement(query.toString())) {
			stmt.setLong(1, usuario.getId());

			try(ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					Long id = rs.getLong("id_peso");
					BigDecimal peso = new BigDecimal(rs.getDouble("vl_peso"));
					LocalDateTime dataRegistro = DateUtil.toDateTime(rs.getString("dt_text"));
					
					Peso registro = new Peso(id, peso, dataRegistro, usuario);
					
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
	public List<Peso> buscaTodos() {
		List<Peso> pesos = new ArrayList<>();
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT P.*, TO_CHAR(P.dt_medida, 'YYYY-MM-DD\"T\"HH24:MI:SS') AS dt_text ");
		builder.append("FROM T_HTK_PESO P ");
		builder.append("ORDER BY P.dt_medida DESC");
		String query = builder.toString();
		
		try (
			PreparedStatement stmt = conexao.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
		){
			while (rs.next()) {
				Long id = rs.getLong("id_peso");
				Usuario usuario = new Usuario(rs.getLong("fk_id_usuario"));
				BigDecimal peso = new BigDecimal(rs.getDouble("vl_peso"));
				LocalDateTime dataRegistro = DateUtil.toDateTime(rs.getString("dt_text"));
				
				Peso registro = new Peso(id, peso, dataRegistro, usuario);
				
				pesos.add(registro);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return Collections.unmodifiableList(pesos);
	}

	@Override
	public void atualiza(Peso registro) throws DBException {
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE T_HTK_PESO P SET");
		builder.append(" P.vl_peso = ?,"); 
		builder.append(" P.dt_medida = TO_DATE(?,'YYYY-MM-DD\"T\"HH24:MI:SS') "); 
		builder.append("WHERE P.id_peso = ? AND P.fk_id_usuario = ?");
		String atualizar = builder.toString();
		
		try(PreparedStatement stmt = conexao.prepareStatement(atualizar)) {
			stmt.setDouble(1, registro.getPeso().doubleValue());
			stmt.setString(2, DateUtil.toText(registro.getDataRegistro()));
			stmt.setLong(3, registro.getId());
			stmt.setLong(4, registro.getUsuario().getId());
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
		String delete = "DELETE FROM T_HTK_PESO P WHERE P.id_peso = ?";
		
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
		String delete = "DELETE FROM T_HTK_PESO";
		
		try(PreparedStatement stmt = conexao.prepareStatement(delete)) {
			stmt.executeUpdate();
		}  
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void fechaConexao() {
		try {
			this.conexao.close();
		} catch (SQLException e) {
			System.out.println("Erro ao fechar conexão.");
			e.printStackTrace();
		}
	}
}
