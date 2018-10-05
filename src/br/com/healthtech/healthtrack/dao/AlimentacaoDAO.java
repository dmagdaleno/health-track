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
import br.com.healthtech.healthtrack.modelo.registro.Alimentacao;
import br.com.healthtech.healthtrack.utils.DateUtil;

/**
 * Classe responsável por abstrair a comunicação com o banco de dados
 * @author dmagdaleno
 *
 */
public class AlimentacaoDAO {
	
	private Connection conexao;
	
	public AlimentacaoDAO() {
		ConnectionManager manager = ConnectionManager.getInstance();
		conexao = manager.obterConexao();
	}
	
	/**
	 * Registra {@link Alimentacao} relacionada com um {@link Usuario}
	 * 
	 * @param alimentacao
	 * 		{@link Alimentacao}
	 */
	public void insert(Alimentacao alimentacao) {
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO T_HTK_ALIMENTO (");
		builder.append(" id_alimento,"); 
		builder.append(" fk_id_usuario,"); 
		builder.append(" fk_id_tp_alimento,"); 
		builder.append(" vl_caloria,"); 
		builder.append(" dt_consumo,"); 
		builder.append(" ds_alimento) "); 
		builder.append("VALUES (?, ?, ?, ?, TO_DATE(?,'DD/MM/YYYY HH24:MI'), ?)");
		String insert = builder.toString();
		
		try(PreparedStatement stmt = conexao.prepareStatement(insert)) {
			stmt.setLong(1, alimentacao.getId());
			stmt.setLong(2, alimentacao.getUsuario().getId());
			stmt.setInt(3, alimentacao.getTipo());
			stmt.setDouble(4, alimentacao.getValorCalorico().doubleValue());
			stmt.setString(5, DateUtil.toText(alimentacao.getDataRegistro()));
			stmt.setString(6, alimentacao.getDescricao());
			stmt.executeUpdate();
		} 
		catch (SQLIntegrityConstraintViolationException e) {
			String msg = String.format("Chave primária [%d] ou estranjeira [%d] inválida", 
					alimentacao.getId(), alimentacao.getUsuario().getId());
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
	 * Registra uma lista de {@link Alimentacao}
	 * 
	 * @param alimentacoes
	 * 		{@link List}<{@link Alimentacao}>
	 */
	public void insertAll(List<Alimentacao> alimentacoes) {
		alimentacoes.forEach(alimentacao -> {
			this.insert(alimentacao);
		});
	}
	
	
	/**
	 * Recupera lista de {@link Alimentacao}<br>
	 * Sem nenhum filtro
	 * 
	 * @return
	 * 		{@link List}<{@link Alimentacao}>
	 */
	public List<Alimentacao> getAll() {
		List<Alimentacao> alimentacoes = new ArrayList<>();
		String query = "SELECT A.*, TO_CHAR(A.dt_consumo, 'DD/MM/YYYY HH24:MI') AS dt_text FROM T_HTK_ALIMENTO A";
		
		try (
			PreparedStatement stmt = conexao.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
		){
			while (rs.next()) {
				Long id = rs.getLong("id_alimento");
				Usuario usuario = new Usuario(rs.getLong("fk_id_usuario"));
				int tipo = rs.getInt("fk_id_tp_alimento");
				BigDecimal calorias = new BigDecimal(rs.getDouble("vl_caloria"));
				LocalDateTime dataRegistro = DateUtil.toDate(rs.getString("dt_text"));
				String descricao = rs.getString("ds_alimento");
				
				Alimentacao alimentacao = new Alimentacao(id, tipo, descricao, calorias, dataRegistro, usuario);
				alimentacoes.add(alimentacao);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return Collections.unmodifiableList(alimentacoes);
	}
	
	/**
	 * Fecha a conexão com o banco
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
