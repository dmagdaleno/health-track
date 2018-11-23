package br.com.healthtech.healthtrack.dao;

import java.util.List;

import br.com.healthtech.healthtrack.exception.DBException;
import br.com.healthtech.healthtrack.modelo.Usuario;
import br.com.healthtech.healthtrack.modelo.registro.PressaoArterial;

public interface PressaoArterialDAO {
	/**
	 * Registra {@link PressaoArterial} relacionado com um {@link Usuario}
	 * 
	 * @param registro
	 * 		{@link PressaoArterial}
	 */
	public void insere(PressaoArterial registro) throws DBException;
	
	/**
	 * Registra uma lista de {@link PressaoArterial}
	 * 
	 * @param registros
	 * 		{@link List}<{@link PressaoArterial}>
	 */
	public void insereTodos(List<PressaoArterial> registros) throws DBException;

	/**
	 * Recupera registro de {@link PressaoArterial} específico por id
	 * 
	 * @param id
	 * 		{@link Long}
	 * 
	 * @return
	 * 		{@link List}<{@link PressaoArterial}>
	 */
	public PressaoArterial buscaPor(Long id) throws DBException;
	
	/**
	 * Recupera lista de {@link PressaoArterial} por {@link Usuario}
	 * 
	 * @param usuario
	 * 		{@link Usuario} 
	 * 
	 * @return
	 * 		{@link List}<{@link PressaoArterial}>
	 */
	public List<PressaoArterial> buscaPor(Usuario usuario) throws DBException;
	
	/**
	 * Recupera lista de {@link PressaoArterial} por {@link Usuario}
	 * Com uma quantidade limitada de registros 
	 * 
	 * @param usuario
	 * 		{@link Usuario} 
	 * 
	 * @param quantidade
	 * 		de registros
	 * 
	 * @return
	 * 		{@link List}<{@link PressaoArterial}>
	 */
	public List<PressaoArterial> buscaPor(Usuario usuario, int quantidade) throws DBException;
	
	/**
	 * Recupera lista de {@link PressaoArterial}<br>
	 * Sem nenhum filtro
	 * 
	 * @return
	 * 		{@link List}<{@link PressaoArterial}>
	 */
	public List<PressaoArterial> buscaTodos();

	/**
	 * Atualiza registro de {@link PressaoArterial} por id
	 * 
	 * @param registro
	 */
	public void atualiza(PressaoArterial registro) throws DBException;
	
	/**
	 * Exclui registro expecífico por id 
	 * 
	 * @param id
	 */
	public void exclui(Long id) throws DBException;
	
	/**
	 * Exclui todos os registros de {@link PressaoArterial}
	 */
	public void excluiTodos() throws DBException;

	/**
	 * Fecha a conexão com o banco de dados
	 */
	public void fechaConexao();
}
