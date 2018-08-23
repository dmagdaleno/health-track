package br.com.healthtech.healthtrack.modelo;

/**
 * Tipos de alimentação pré definidos
 * @author dmagdaleno
 *
 */
public enum TipoAlimentacao {
	CAFE_DA_MANHA("Café da manhã"), 
	ALMOCO("Almoço"), 
	JANTAR("Jantar"), 
	LANCHE_LEVE("Lanche leve"), 
	FRUTA("Fruta");
	
	private final String valor;
	
	TipoAlimentacao(String valor) {
		this.valor = valor;
	}
	
	public String getValor() {
		return this.valor;
	}
}
