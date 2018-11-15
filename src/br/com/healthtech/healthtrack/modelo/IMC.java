package br.com.healthtech.healthtrack.modelo;

import java.math.BigDecimal;

/**
 * Classe responsável por calcular o IMC do usuário
 * 
 * @author dmagdaleno
 *
 */
public class IMC {
	private BigDecimal massa;
	private BigDecimal altura;
	
	/**
	 * Instancia classe IMC com base na massa e na altura do usuário
	 * 
	 * @param massa
	 * 	massa (peso) do usuário
	 * @param altura
	 * 	altura do usuário
	 */
	public IMC(BigDecimal massa, BigDecimal altura) {
		this.massa = massa;
		this.altura = altura;
	}
	
	/**
	 * Calcula o IMC de acordo com a massa e a altura informadas
	 * 
	 * @return
	 *  Valor do IMC em {@link BigDecimal}
	 */
	public BigDecimal calcula() {
		return massa.divide(altura.pow(2), 2, BigDecimal.ROUND_HALF_EVEN);
	}
}
