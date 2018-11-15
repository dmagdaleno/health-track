package br.com.healthtech.healthtrack.dao;

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

import br.com.healthtech.healthtrack.db.ConnectionManager;
import br.com.healthtech.healthtrack.modelo.Usuario;
import br.com.healthtech.healthtrack.modelo.registro.PressaoArterial;
import br.com.healthtech.healthtrack.utils.DateUtil;

/**
 * Classe responsável por abstrair a comunicação com o banco de dados
 * da entidade {@link PressaoArterial}
 * 
 * @author dmagdaleno
 *
 */
public class PressaoArterialDAO {
	
	private Connection conexao;
	
	public PressaoArterialDAO() {
		ConnectionManager manager = ConnectionManager.getInstance();
		conexao = manager.obterConexao();
	}
	
	/**
	 * Registra {@link PressaoArterial} relacionada com um {@link Usuario}
	 * 
	 * @param registro
	 * 		{@link PressaoArterial}
	 */
	public void insert(PressaoArterial registro) {
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO T_HTK_PRESSAO (");
		builder.append(" id_pressao,"); 
		builder.append(" fk_id_usuario,"); 
		builder.append(" vl_pressao_max,"); 
		builder.append(" vl_pressao_min,"); 
		builder.append(" dt_medida) "); 
		builder.append("VALUES (?, ?, ?, ?, TO_DATE(?,'DD/MM/YYYY HH24:MI'))");
		String insert = builder.toString();
		
		try(PreparedStatement stmt = conexao.prepareStatement(insert)) {
			stmt.setLong(1, registro.getId());
			stmt.setLong(2, registro.getUsuario().getId());
			stmt.setDouble(3, registro.getPressaoMaxima().doubleValue());
			stmt.setDouble(4, registro.getPressaoMinima().doubleValue());
			stmt.setString(5, DateUtil.toText(registro.getDataRegistro()));
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
	
	/**
	 * Registra uma lista de {@link PressaoArterial}
	 * 
	 * @param pressoes
	 * 		{@link List}<{@link PressaoArterial}>
	 */
	public void insertAll(List<PressaoArterial> pressoes) {
		pressoes.forEach(pressao -> {
			this.insert(pressao);
		});
	}
	
	
	/**
	 * Recupera lista de {@link PressaoArterial}<br>
	 * Sem nenhum filtro
	 * 
	 * @return
	 * 		{@link List}<{@link PressaoArterial}>
	 */
	public List<PressaoArterial> getAll() {
		List<PressaoArterial> pressoes = new ArrayList<>();
		String query = "SELECT P.*, TO_CHAR(P.dt_medida, 'DD/MM/YYYY HH24:MI') AS dt_text FROM T_HTK_PRESSAO P";
		
		try (
			PreparedStatement stmt = conexao.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
		){
			while (rs.next()) {
				Long id = rs.getLong("id_pressao");
				Usuario usuario = new Usuario(rs.getLong("fk_id_usuario"));
				BigDecimal pressaoMax = new BigDecimal(rs.getDouble("vl_pressao_max"));
				BigDecimal pressaoMin = new BigDecimal(rs.getDouble("vl_pressao_min"));
				LocalDateTime dataRegistro = DateUtil.toDate(rs.getString("dt_text"));
				
				PressaoArterial registro = new PressaoArterial(id, pressaoMax, pressaoMin, dataRegistro, usuario);
				
				pressoes.add(registro);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return Collections.unmodifiableList(pressoes);
	}
	
	/**
	 * Remove todos os registros de peso
	 */
	public void deleteAll() {
		String delete = "DELETE FROM T_HTK_PRESSAO";
		
		try(PreparedStatement stmt = conexao.prepareStatement(delete)) {
			stmt.executeUpdate();
		}  
		catch (SQLException e) {
			e.printStackTrace();
		} 
		catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * Fecha a conexão com o banco de dados
	 */
	public void fechaConexao() {
		try {
			this.conexao.close();
		} catch (SQLException e) {
			System.out.println("Erro ao fechar conexão.");
			e.printStackTrace();
		}
	}

}
