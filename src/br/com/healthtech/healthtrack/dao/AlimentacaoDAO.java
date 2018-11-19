package br.com.healthtech.healthtrack.dao;

import java.util.List;

import br.com.healthtech.healthtrack.exception.DBException;
import br.com.healthtech.healthtrack.modelo.Usuario;
import br.com.healthtech.healthtrack.modelo.registro.Alimentacao;

public interface AlimentacaoDAO {
	/**
	 * Registra {@link Alimentacao} relacionada com um {@link Usuario}
	 * 
	 * @param registro
	 * 		{@link Alimentacao}
	 */
	public void insere(Alimentacao registro) throws DBException;
	
	/**
	 * Registra uma lista de {@link Alimentacao}
	 * 
	 * @param registros
	 * 		{@link List}<{@link Alimentacao}>
	 */
	public void insereTodos(List<Alimentacao> registros) throws DBException;

	/**
	 * Recupera registro de {@link Alimentacao} específico por id
	 * 
	 * @param id
	 * 		{@link Long}
	 * 
	 * @return
	 * 		{@link List}<{@link Alimentacao}>
	 */
	public Alimentacao buscaPor(Long id) throws DBException;
	
	/**
	 * Recupera lista de {@link Alimentacao} por {@link Usuario}
	 * 
	 * @param usuario
	 * 		{@link Usuario} 
	 * 
	 * @return
	 * 		{@link List}<{@link Alimentacao}>
	 */
	public List<Alimentacao> buscaPor(Usuario usuario) throws DBException;
	
	/**
	 * Recupera lista de {@link Alimentacao}<br>
	 * Sem nenhum filtro
	 * 
	 * @return
	 * 		{@link List}<{@link Alimentacao}>
	 */
	public List<Alimentacao> buscaTodos();

	/**
	 * Atualiza registro de {@link Alimentacao} por id
	 * 
	 * @param registro
	 */
	public void atualiza(Alimentacao registro) throws DBException;
	
	/**
	 * Exclui registro expecífico por id 
	 * 
	 * @param id
	 */
	public void exclui(Long id) throws DBException;
	
	/**
	 * Exclui todos os registros de {@link Alimentacao}
	 */
	public void excluiTodos() throws DBException;

	/**
	 * Fecha a conexão com o banco de dados
	 */
	public void fechaConexao();
}
