package br.com.healthtech.healthtrack.modelo;

import java.math.BigDecimal;

public class IMC {
	private BigDecimal massa;
	private BigDecimal altura;
	
	public IMC(BigDecimal massa, BigDecimal altura) {
		this.massa = massa;
		this.altura = altura;
	}
	
	public BigDecimal calcula() {
		return massa.divide(altura.pow(2));
	}
}
