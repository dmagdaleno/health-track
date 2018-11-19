package br.com.healthtech.healthtrack.dao;

import java.util.List;

import br.com.healthtech.healthtrack.exception.DBException;
import br.com.healthtech.healthtrack.modelo.Usuario;
import br.com.healthtech.healthtrack.modelo.registro.AtividadeFisica;

public interface AtividadeFisicaDAO {
	
	/**
	 * Registra {@link AtividadeFisica} relacionada com um {@link Usuario}
	 * 
	 * @param registro
	 * 		{@link AtividadeFisica}
	 */
	public void insere(AtividadeFisica registro) throws DBException;
	
	/**
	 * Registra uma lista de {@link AtividadeFisica}
	 * 
	 * @param registros
	 * 		{@link List}<{@link AtividadeFisica}>
	 */
	public void insereTodos(List<AtividadeFisica> registros) throws DBException;

	/**
	 * Recupera registro de {@link AtividadeFisica} específico por id
	 * 
	 * @param id
	 * 		{@link Long}
	 * 
	 * @return
	 * 		{@link List}<{@link AtividadeFisica}>
	 */
	public AtividadeFisica buscaPor(Long id) throws DBException;
	
	/**
	 * Recupera lista de {@link AtividadeFisica} por {@link Usuario}
	 * 
	 * @param usuario
	 * 		{@link Usuario} 
	 * 
	 * @return
	 * 		{@link List}<{@link AtividadeFisica}>
	 */
	public List<AtividadeFisica> buscaPor(Usuario usuario) throws DBException;
	
	/**
	 * Recupera lista de {@link AtividadeFisica}<br>
	 * Sem nenhum filtro
	 * 
	 * @return
	 * 		{@link List}<{@link AtividadeFisica}>
	 */
	public List<AtividadeFisica> buscaTodos();

	/**
	 * Atualiza registro de {@link AtividadeFisica} por id
	 * 
	 * @param registro
	 */
	public void atualiza(AtividadeFisica registro) throws DBException;
	
	/**
	 * Exclui registro expecífico por id 
	 * 
	 * @param id
	 */
	public void exclui(Long id) throws DBException;
	
	/**
	 * Exclui todos os registros de {@link AtividadeFisica}
	 */
	public void excluiTodos() throws DBException;

	/**
	 * Fecha a conexão com o banco de dados
	 */
	public void fechaConexao();

}
