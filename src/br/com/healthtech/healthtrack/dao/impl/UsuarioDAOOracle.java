package br.com.healthtech.healthtrack.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.healthtech.healthtrack.dao.UsuarioDAO;
import br.com.healthtech.healthtrack.db.ConnectionManager;
import br.com.healthtech.healthtrack.exception.DBException;
import br.com.healthtech.healthtrack.modelo.Login;
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
	public void insereTodos(List<Usuario> usuarios) throws DBException {
		usuarios.forEach(usuario -> {
			try {
				this.insere(usuario);
			} catch (DBException e) {
				System.out.println("Não foi possível inserir: " + usuario);
			}
		});

	}

	@Override
	public Usuario buscaPor(Long id) throws DBException {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT T.*, ");
		builder.append(" TO_CHAR(T.dt_nascimento, 'YYYY-MM-DD') AS dt_nasc_text, ");
		builder.append(" TO_CHAR(T.dt_ult_login, 'YYYY-MM-DD\"T\"HH24:MI:SS') AS dt_login_text ");
		builder.append("FROM T_HTK_USUARIO T ");
		builder.append("WHERE T.id_usuario = ?");
		String query = builder.toString();
		
		Usuario usuario = null;
		try (PreparedStatement stmt = conexao.prepareStatement(query)){
			stmt.setLong(1, id);
			
			try(ResultSet rs = stmt.executeQuery()) {
				if(rs.next()) {
					String email = rs.getString("ds_email");
					String senha = rs.getString("cd_senha");
					String nome = rs.getString("nm_usuario");
					LocalDate dataNascimento = DateUtil.toDate(rs.getString("dt_nasc_text"));
					BigDecimal altura = new BigDecimal(rs.getDouble("vl_altura"));
					String genero = rs.getString("cd_genero");
					BigDecimal limiteCaloria = new BigDecimal(rs.getDouble("vl_limite_caloria"));
					LocalDateTime ultimoLogin = DateUtil.toDateTime(rs.getString("dt_login_text"));
					
					Login login = new Login(email, senha, ultimoLogin);
					usuario = new Usuario(id, nome, dataNascimento, genero, altura, limiteCaloria, login);
				}
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return usuario;
	}

	@Override
	public List<Usuario> buscaTodos() {
		List<Usuario> usuarios = new ArrayList<>();
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT T.*, ");
		builder.append(" TO_CHAR(T.dt_nascimento, 'YYYY-MM-DD') AS dt_nasc_text, ");
		builder.append(" TO_CHAR(T.dt_ult_login, 'YYYY-MM-DD\"T\"HH24:MI:SS') AS dt_login_text ");
		builder.append("FROM T_HTK_USUARIO T ");
		builder.append("ORDER BY T.dt_ult_login DESC");
		String query = builder.toString();
		
		try (
			PreparedStatement stmt = conexao.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
		){
			while (rs.next()) {
				Long id = rs.getLong("id_usuario");
				String email = rs.getString("ds_email");
				String senha = rs.getString("cd_senha");
				String nome = rs.getString("nm_usuario");
				LocalDate dataNascimento = DateUtil.toDate(rs.getString("dt_nasc_text"));
				BigDecimal altura = new BigDecimal(rs.getDouble("vl_altura"));
				String genero = rs.getString("cd_genero");
				BigDecimal limiteCaloria = new BigDecimal(rs.getDouble("vl_limite_caloria"));
				LocalDateTime ultimoLogin = DateUtil.toDateTime(rs.getString("dt_login_text"));
				
				Login login = new Login(email, senha, ultimoLogin);
				Usuario usuario = new Usuario(id, nome, dataNascimento, genero, altura, limiteCaloria, login);
				
				usuarios.add(usuario);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return Collections.unmodifiableList(usuarios);
	}

	@Override
	public void atualiza(Usuario usuario) throws DBException {
		StringBuilder builder = new StringBuilder();
		builder.append("UPDATE T_HTK_USUARIO T SET");
		builder.append(" T.ds_email = ?,"); 
		builder.append(" T.cd_senha = ?,"); 
		builder.append(" T.nm_usuario = ?,"); 
		builder.append(" T.dt_nascimento = TO_DATE(?,'YYYY-MM-DD'),");
		builder.append(" T.vl_altura = ?,");
		builder.append(" T.cd_genero = ?,");
		builder.append(" T.vl_limite_caloria = ?,");
		builder.append(" T.dt_ult_login = TO_DATE(?,'YYYY-MM-DD\"T\"HH24:MI:SS') "); 
		builder.append("WHERE T.id_usuario = ?");
		String atualizar = builder.toString();
		
		try(PreparedStatement stmt = conexao.prepareStatement(atualizar)) {
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
	public void exclui(Long id) throws DBException {
		String delete = "DELETE FROM T_HTK_USUARIO T WHERE T.id_usuario = ?";
		
		try(PreparedStatement stmt = conexao.prepareStatement(delete)) {
			stmt.setLong(1, id);
			stmt.executeUpdate();
		}  
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void excluiTodos() throws DBException {
		String delete = "DELETE FROM T_HTK_USUARIO";
		
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
