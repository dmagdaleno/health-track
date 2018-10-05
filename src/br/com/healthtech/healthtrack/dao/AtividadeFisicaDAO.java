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
import br.com.healthtech.healthtrack.modelo.registro.AtividadeFisica;
import br.com.healthtech.healthtrack.utils.DateUtil;

/**
 * Classe responsável por abstrair a comunicação com o banco de dados
 * da entidade {@link AtividadeFisica}
 * 
 * @author dmagdaleno
 *
 */
public class AtividadeFisicaDAO {
	
	private Connection conexao;
	
	public AtividadeFisicaDAO() {
		ConnectionManager manager = ConnectionManager.getInstance();
		conexao = manager.obterConexao();
	}
	
	/**
	 * Registra {@link AtividadeFisica} relacionada com um {@link Usuario}
	 * 
	 * @param atividade
	 * 		{@link AtividadeFisica}
	 */
	public void insert(AtividadeFisica atividade) {
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO T_HTK_ATV_FISICA (");
		builder.append(" id_atv_fisica,"); 
		builder.append(" fk_id_usuario,"); 
		builder.append(" fk_id_tp_atv_fisica,"); 
		builder.append(" vl_caloria,"); 
		builder.append(" dt_atv_fisica,"); 
		builder.append(" ds_atv_fisica) "); 
		builder.append("VALUES (?, ?, ?, ?, TO_DATE(?,'DD/MM/YYYY HH24:MI'), ?)");
		String insert = builder.toString();
		
		try(PreparedStatement stmt = conexao.prepareStatement(insert)) {
			stmt.setLong(1, atividade.getId());
			stmt.setLong(2, atividade.getUsuario().getId());
			stmt.setInt(3, atividade.getTipo());
			stmt.setDouble(4, atividade.getGastoCalorico().doubleValue());
			stmt.setString(5, DateUtil.toText(atividade.getDataRegistro()));
			stmt.setString(6, atividade.getDescricao());
			stmt.executeUpdate();
		} 
		catch (SQLIntegrityConstraintViolationException e) {
			String msg = String.format("Chave primária [%d] ou estranjeira [%d] inválida", 
					atividade.getId(), atividade.getUsuario().getId());
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
	 * Registra uma lista de {@link AtividadeFisica}
	 * 
	 * @param atividades
	 * 		{@link List}<{@link AtividadeFisica}>
	 */
	public void insertAll(List<AtividadeFisica> atividades) {
		atividades.forEach(atividade -> {
			this.insert(atividade);
		});
	}
	
	
	/**
	 * Recupera lista de {@link AtividadeFisica}<br>
	 * Sem nenhum filtro
	 * 
	 * @return
	 * 		{@link List}<{@link AtividadeFisica}>
	 */
	public List<AtividadeFisica> getAll() {
		List<AtividadeFisica> atividades = new ArrayList<>();
		String query = "SELECT A.*, TO_CHAR(A.dt_atv_fisica, 'DD/MM/YYYY HH24:MI') AS dt_text FROM T_HTK_ATV_FISICA A";
		
		try (
			PreparedStatement stmt = conexao.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
		){
			while (rs.next()) {
				Long id = rs.getLong("id_atv_fisica");
				Usuario usuario = new Usuario(rs.getLong("fk_id_usuario"));
				int tipo = rs.getInt("fk_id_tp_atv_fisica");
				BigDecimal calorias = new BigDecimal(rs.getDouble("vl_caloria"));
				LocalDateTime dataRegistro = DateUtil.toDate(rs.getString("dt_text"));
				String descricao = rs.getString("ds_atv_fisica");
				
				AtividadeFisica atividade = 
						new AtividadeFisica(id, tipo, descricao, calorias, dataRegistro, usuario);
				
				atividades.add(atividade);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return Collections.unmodifiableList(atividades);
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
