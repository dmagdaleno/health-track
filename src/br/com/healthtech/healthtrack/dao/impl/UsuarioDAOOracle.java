package br.com.healthtech.healthtrack.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import br.com.healthtech.healthtrack.dao.UsuarioDAO;
import br.com.healthtech.healthtrack.db.ConnectionManager;
import br.com.healthtech.healthtrack.exception.DBException;
import br.com.healthtech.healthtrack.modelo.Usuario;
import br.com.healthtech.healthtrack.utils.DateUtil;

public class UsuarioDAOOracle implements UsuarioDAO {
	
	private Connection conexao;
	
	public UsuarioDAOOracle() {
		ConnectionManager manager = ConnectionManager.getInstance();
		conexao = manager.obterConexao();
	}

	@Override
	public void insere(Usuario usuario) throws DBException {
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO T_HTK_USUARIO (");
		builder.append(" id_usuario,"); 
		builder.append(" ds_email,"); 
		builder.append(" cd_senha,"); 
		builder.append(" nm_usuario,"); 
		builder.append(" dt_nascimento,");
		builder.append(" vl_altura,");
		builder.append(" cd_genero,");
		builder.append(" vl_limite_caloria,");
		builder.append(" dt_ult_login) "); 
		builder.append("VALUES (SQ_TB_USUARIO.NEXTVAL, ?, ?, ?, TO_DATE(?,'YYYY-MM-DD'), ?, ?, ?, TO_DATE(?,'YYYY-MM-DD\"T\"HH24:MI:SS'))");
		String insert = builder.toString();
		
		try(PreparedStatement stmt = conexao.prepareStatement(insert)) {
			stmt.setString(1, usuario.getLogin().getEmail());
			stmt.setString(2, usuario.getLogin().getSenha());
			stmt.setString(3, usuario.getNome());
			stmt.setString(4, DateUtil.toText(usuario.getDataNascimento()));
			stmt.setDouble(5, usuario.getAltura().doubleValue());
			stmt.setString(6, usuario.getGenero());
			stmt.setDouble(7, usuario.getLimiteCaloria().doubleValue());
			stmt.setString(8, DateUtil.toText(usuario.getLogin().getUltimoLogin()));
			stmt.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new DBException(e);
		}
	}

	@Override
	public void insereTodos(List<Usuario> registros) throws DBException {
		// TODO Auto-generated method stub

	}

	@Override
	public Usuario buscaPor(Long id) throws DBException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> buscaPor(Usuario usuario) throws DBException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> buscaTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void atualiza(Usuario registro) throws DBException {
		// TODO Auto-generated method stub

	}

	@Override
	public void exclui(Long id) throws DBException {
		// TODO Auto-generated method stub

	}

	@Override
	public void excluiTodos() throws DBException {
		// TODO Auto-generated method stub

	}

	@Override
	public void fechaConexao() {
		// TODO Auto-generated method stub

	}

}
