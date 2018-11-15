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
import br.com.healthtech.healthtrack.modelo.registro.Peso;
import br.com.healthtech.healthtrack.utils.DateUtil;

/**
 * Classe responsável por abstrair a comunicação com o banco de dados
 * da entidade {@link Peso}
 * 
 * @author dmagdaleno
 *
 */
public class PesoDAO {
	
	private Connection conexao;
	
	public PesoDAO() {
		ConnectionManager manager = ConnectionManager.getInstance();
		conexao = manager.obterConexao();
	}
	
	/**
	 * Registra {@link Peso} relacionado com um {@link Usuario}
	 * 
	 * @param registro
	 * 		{@link Peso}
	 */
	public void insert(Peso registro) {
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
		} 
		catch (SQLException e) {
			e.printStackTrace();
		} 
		catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
	
	/**
	 * Registra uma lista de {@link Peso}
	 * 
	 * @param pesos
	 * 		{@link List}<{@link Peso}>
	 */
	public void insertAll(List<Peso> pesos) {
		pesos.forEach(peso -> {
			this.insert(peso);
		});
	}
	
	
	/**
	 * Recupera lista de {@link Peso}<br>
	 * Sem nenhum filtro
	 * 
	 * @return
	 * 		{@link List}<{@link Peso}>
	 */
	public List<Peso> getAll() {
		List<Peso> pesos = new ArrayList<>();
		String query = "SELECT P.*, TO_CHAR(P.dt_medida, 'YYYY-MM-DD\"T\"HH24:MI:SS') AS dt_text FROM T_HTK_PESO P";
		
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
	
	/**
	 * Remove todos os registros de peso
	 */
	public void deleteAll() {
		String delete = "DELETE FROM T_HTK_PESO";
		
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
