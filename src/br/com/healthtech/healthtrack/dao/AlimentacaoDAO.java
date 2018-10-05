package br.com.healthtech.healthtrack.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.healthtech.healthtrack.db.ConnectionManager;
import br.com.healthtech.healthtrack.modelo.registro.Alimentacao;

public class AlimentacaoDAO {
	
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	
	private Connection conexao;
	
	public AlimentacaoDAO() {
		ConnectionManager manager = ConnectionManager.getInstance();
		conexao = manager.obterConexao();
	}
	
	public void add(Alimentacao alimentacao) {
		System.out.println("preparando inserção..");
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
		
		try {
			PreparedStatement stmt = conexao.prepareStatement(insert);
			stmt.setLong(1, alimentacao.getId());
			stmt.setLong(2, alimentacao.getUsuario().getId());
			stmt.setInt(3, alimentacao.getTipo());
			stmt.setDouble(4, alimentacao.getValorCalorico().doubleValue());
			stmt.setString(5, alimentacao.getDataRegistro().format(formatter));
			stmt.setString(6, alimentacao.getDescricao());
			System.out.println("executando..");
			stmt.executeUpdate();
			System.out.println("inserido");
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
	
	public void add(List<Alimentacao> alimentacoes) {
		for(Alimentacao alimentacao: alimentacoes) {
			this.add(alimentacao);
		}
	}
	
	public List<Alimentacao> getAll() {
		List<Alimentacao> alimentacoes = new ArrayList<>();
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conexao.prepareStatement("SELECT * FROM TAB_COLABORADOR");
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				int codigo = rs.getInt("CODIGO_COLABORADOR");
				String nome = rs.getString("NOME");
				String email = rs.getString("EMAIL");
				double salario = rs.getDouble("SALARIO");
				java.sql.Date data = rs.getDate("DATA_CONTRATACAO");
				Calendar dataContratacao = Calendar.getInstance();
				dataContratacao.setTimeInMillis(data.getTime());
				
				Alimentacao alimentacao = new Alimentacao(null, null);
				alimentacoes.add(alimentacao);
			}
		} catch (SQLException e) {
		e.printStackTrace();
		}finally {
		try {
		stmt.close();
		rs.close();
		conexao.close();
		} catch (SQLException e) {
		e.printStackTrace();
		}
		}
		
		return Collections.unmodifiableList(alimentacoes);
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
