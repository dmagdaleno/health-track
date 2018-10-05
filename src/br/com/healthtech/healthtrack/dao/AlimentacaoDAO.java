package br.com.healthtech.healthtrack.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import br.com.healthtech.healthtrack.db.ConnectionManager;
import br.com.healthtech.healthtrack.modelo.registro.Alimentacao;

public class AlimentacaoDAO {
	
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
			stmt.setLong(3, alimentacao.getTipo().ordinal()+1);
			stmt.setDouble(4, alimentacao.getValorCalorico().doubleValue());
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
			String dataFormatada = alimentacao.getDataRegistro().format(formatter);
			stmt.setString(5, dataFormatada);
			stmt.setString(6, alimentacao.getDescricao());
			System.out.println("executando..");
			stmt.executeUpdate();
			System.out.println("inserido");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void add(List<Alimentacao> alimentacoes) {
		for(Alimentacao alimentacao: alimentacoes) {
			this.add(alimentacao);
		}
	}
	
	public List<Alimentacao> getAll() {
		return null;
	}

}
