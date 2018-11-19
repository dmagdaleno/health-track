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
	public void insere(PressaoArterial registro) {
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
		} 
		catch (SQLException e) {
			e.printStackTrace();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insereTodos(List<PressaoArterial> registros) {
		registros.forEach(registro -> {
			this.insere(registro);
		});
	}

	@Override
	public PressaoArterial buscaPor(Long id) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT P.*, TO_CHAR(P.dt_medida, 'YYYY-MM-DD\"T\"HH24:MI:SS') AS dt_text ");
		query.append("FROM T_HTK_PRESSAO P ");
		query.append("WHERE P.id_pressao = ?");
		
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
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return registro;
	}

	@Override
	public List<PressaoArterial> buscaPor(Usuario usuario) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT P.*, TO_CHAR(P.dt_medida, 'YYYY-MM-DD\"T\"HH24:MI:SS') AS dt_text ");
		query.append("FROM T_HTK_PRESSAO P ");
		query.append("WHERE P.fk_id_usuario = ?");
		
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
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return registros;
	}

	@Override
	public List<PressaoArterial> buscaTodos() {
		List<PressaoArterial> registros = new ArrayList<>();
		String query = "SELECT P.*, TO_CHAR(P.dt_medida, 'YYYY-MM-DD\"T\"HH24:MI:SS') AS dt_text FROM T_HTK_PRESSAO P";
		
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
	public void atualiza(PressaoArterial registro) {
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE T_HTK_PRESSAO P SET");
		builder.append(" P.vl_pressao_max = ?,"); 
		builder.append(" P.vl_pressao_min = ?,"); 
		builder.append(" P.dt_medida = TO_DATE(?,'YYYY-MM-DD\"T\"HH24:MI:SS') "); 
		builder.append("WHERE P.id_pressao = ? AND P.fk_id_usuario = ?");
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
		catch (SQLException e) {
			e.printStackTrace();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void exclui(Long id) {
		String delete = "DELETE FROM T_HTK_PRESSAO P WHERE P.id_pressao = ?";
		
		try(PreparedStatement stmt = conexao.prepareStatement(delete)) {
			stmt.setLong(1, id);
			stmt.executeUpdate();
		}  
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void excluiTodos() {
		String delete = "DELETE FROM T_HTK_PRESSAO";
		
		try(PreparedStatement stmt = conexao.prepareStatement(delete)) {
			stmt.executeUpdate();
		}  
		catch (SQLException e) {
			e.printStackTrace();
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