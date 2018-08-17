package br.com.healthtech.healthtrack.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Abstrai informações sobre o um registro de Peso
 * @author dmagdaleno
 *
 */
public class Peso {
	private BigDecimal peso;
	private LocalDate dataMedida;
	
	public Peso() {
	}
	
	public Peso(BigDecimal peso, LocalDate dataMedida) {
		this.peso = peso;
		this.dataMedida = dataMedida;
	}

	public BigDecimal getPeso() {
		return peso;
	}
	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}
	public LocalDate getDataMedida() {
		return dataMedida;
	}
	public void setDataMedida(LocalDate dataMedida) {
		this.dataMedida = dataMedida;
	}
	
	@Override
	public String toString() {
		return "{ peso=" + peso + ", dataMedida=" + dataMedida + " }";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataMedida == null) ? 0 : dataMedida.hashCode());
		result = prime * result + ((peso == null) ? 0 : peso.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Peso other = (Peso) obj;
		if (dataMedida == null) {
			if (other.dataMedida != null)
				return false;
		} else if (!dataMedida.equals(other.dataMedida))
			return false;
		if (peso == null) {
			if (other.peso != null)
				return false;
		} else if (!peso.equals(other.peso))
			return false;
		return true;
	}
}
