package br.com.healthtech.healthtrack.modelo.registro;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Abstrai informações sobre um registro de Peso
 * @author dmagdaleno
 *
 */
public class Peso extends Registro {
	private BigDecimal peso;
	
	public Peso() {
		super(null);
	}
	
	public Peso(BigDecimal peso, LocalDateTime dataRegistro) {
		super(dataRegistro);
		this.peso = peso;
	}

	public BigDecimal getPeso() {
		return peso;
	}
	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}
	public LocalDateTime getDataRegistro() {
		return super.getData();
	}
	public void setDataRegistro(LocalDateTime dataRegistro) {
		super.setData(dataRegistro);
	}
	
	@Override
	public String toString() {
		return "{ peso=" + peso + ", dataRegistro=" + getDataRegistro() + " }";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((peso == null) ? 0 : peso.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Peso other = (Peso) obj;
		if (peso == null) {
			if (other.peso != null)
				return false;
		} else if (!peso.equals(other.peso))
			return false;
		return true;
	}
	
	
}
