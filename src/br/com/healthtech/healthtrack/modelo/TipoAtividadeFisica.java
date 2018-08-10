package br.com.healthtech.healthtrack.modelo;

public enum TipoAtividadeFisica {
	CAMINHADA("Caminhada"), 
	CORRIDA("Corrida"), 
	PEDALADA("Pedalada"), 
	MUSCULACAO("Musculação");
	
	private final String valor;
	
	private TipoAtividadeFisica(String valor) {
		this.valor = valor;
	}
	
	public String getValor() {
		return this.valor;
	}
}
