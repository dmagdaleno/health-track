package br.com.healthtech.healthtrack.dao;

import java.util.List;

import br.com.healthtech.healthtrack.exception.DBException;
import br.com.healthtech.healthtrack.modelo.Usuario;
import br.com.healthtech.healthtrack.modelo.registro.Peso;

public interface PesoDAO {
	
	/**
	 * Registra {@link Peso} relacionado com um {@link Usuario}
	 * 
	 * @param registro
	 * 		{@link Peso}
	 */
	public void insere(Peso registro) throws DBException;
	
	/**
	 * Registra uma lista de {@link Peso}
	 * 
	 * @param registros
	 * 		{@link List}<{@link Peso}>
	 */
	public void insereTodos(List<Peso> registros) throws DBException;

	/**
	 * Recupera registro de {@link Peso} específico por id
	 * 
	 * @param id
	 * 		{@link Long}
	 * 
	 * @return
	 * 		{@link List}<{@link Peso}>
	 */
	public Peso buscaPor(Long id) throws DBException;
	
	/**
	 * Recupera lista de {@link Peso} por {@link Usuario}
	 * 
	 * @param usuario
	 * 		{@link Usuario} 
	 * 
	 * @return
	 * 		{@link List}<{@link Peso}>
	 */
	public List<Peso> buscaPor(Usuario usuario) throws DBException;
	
	/**
	 * Recupera lista de {@link Peso} por {@link Usuario}
	 * Com uma quantidade limitada de registros 
	 * 
	 * @param usuario
	 * 		{@link Usuario} 
	 * 
	 * @param quantidade
	 * 		de registros
	 * 
	 * @return
	 * 		{@link List}<{@link Peso}>
	 */
	public List<Peso> buscaPor(Usuario usuario, int quantidade) throws DBException;
	
	/**
	 * Recupera lista de {@link Peso}<br>
	 * Sem nenhum filtro
	 * 
	 * @return
	 * 		{@link List}<{@link Peso}>
	 */
	public List<Peso> buscaTodos();

	/**
	 * Atualiza registro de {@link Peso} por id
	 * 
	 * @param registro
	 */
	public void atualiza(Peso registro) throws DBException;
	
	/**
	 * Exclui registro expecífico por id 
	 * 
	 * @param id
	 */
	public void exclui(Long id) throws DBException;
	
	/**
	 * Exclui todos os registros de {@link Peso}
	 */
	public void excluiTodos() throws DBException;

	/**
	 * Fecha a conexão com o banco de dados
	 */
	public void fechaConexao();
}
