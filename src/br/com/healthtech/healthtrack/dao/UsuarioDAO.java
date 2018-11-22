package br.com.healthtech.healthtrack.dao;

import java.util.List;

import br.com.healthtech.healthtrack.exception.DBException;
import br.com.healthtech.healthtrack.modelo.Usuario;

public interface UsuarioDAO {
	/**
	 * Registra {@link Usuario} relacionada com um {@link Usuario}
	 * 
	 * @param usuario
	 * 		{@link Usuario}
	 */
	public void insere(Usuario usuario) throws DBException;
	
	/**
	 * Registra uma lista de {@link Usuario}
	 * 
	 * @param usuarios
	 * 		{@link List}<{@link Usuario}>
	 */
	public void insereTodos(List<Usuario> usuarios) throws DBException;

	/**
	 * Recupera registro de {@link Usuario} específico por id
	 * 
	 * @param id
	 * 		{@link Long}
	 * 
	 * @return
	 * 		{@link List}<{@link Usuario}>
	 */
	public Usuario buscaPor(Long id) throws DBException;
	
	/**
	 * Recupera lista de {@link Usuario}<br>
	 * Sem nenhum filtro
	 * 
	 * @return
	 * 		{@link List}<{@link Usuario}>
	 */
	public List<Usuario> buscaTodos();

	/**
	 * Atualiza registro de {@link Usuario} por id
	 * 
	 * @param usuario
	 */
	public void atualiza(Usuario usuario) throws DBException;
	
	/**
	 * Exclui registro expecífico por id 
	 * 
	 * @param id
	 */
	public void exclui(Long id) throws DBException;
	
	/**
	 * Exclui todos os registros de {@link Usuario}
	 */
	public void excluiTodos() throws DBException;

	/**
	 * Fecha a conexão com o banco de dados
	 */
	public void fechaConexao();
}
