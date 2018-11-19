package br.com.healthtech.healthtrack.dao;

import java.util.List;

import br.com.healthtech.healthtrack.modelo.Usuario;
import br.com.healthtech.healthtrack.modelo.registro.Peso;

public interface PesoDAO {
	
	/**
	 * Registra {@link Peso} relacionado com um {@link Usuario}
	 * 
	 * @param registro
	 * 		{@link Peso}
	 */
	public void insere(Peso registro);
	
	/**
	 * Registra uma lista de {@link Peso}
	 * 
	 * @param pesos
	 * 		{@link List}<{@link Peso}>
	 */
	public void insereTodos(List<Peso> pesos);

	/**
	 * Recupera específico {@link Peso} por id
	 * 
	 * @return
	 * 		{@link List}<{@link Peso}>
	 */
	public Peso buscaPor(Long id);
	
	/**
	 * Recupera lista de {@link Peso} por {@link Usuario}
	 * 
	 * @param usuario
	 * 		{@link Usuario} 
	 * 
	 * @return
	 * 		{@link List}<{@link Peso}>
	 */
	public List<Peso> buscaPor(Usuario usuario);	
	
	/**
	 * Recupera lista de {@link Peso}<br>
	 * Sem nenhum filtro
	 * 
	 * @return
	 * 		{@link List}<{@link Peso}>
	 */
	public List<Peso> buscaTodos();

	/**
	 * Atualiza registro de peso por id
	 * 
	 * @param registro
	 */
	public void atualiza(Peso registro);
	
	/**
	 * Exclui todos os registros de peso
	 */
	public void excluiTodos();

	/**
	 * Exclui registro expecífico por id 
	 * 
	 * @param id
	 */
	public void exclui(Long id);

	/**
	 * Fecha a conexão com o banco de dados
	 */
	public void fechaConexao();
}
