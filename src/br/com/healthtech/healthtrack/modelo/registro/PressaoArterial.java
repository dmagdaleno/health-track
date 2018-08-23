package br.com.healthtech.healthtrack.modelo.registro;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Abstrai informações sobre um registro de Pressão Arterial
 * @author dmagdaleno
 *
 */
public class PressaoArterial extends Registro {
	private BigDecimal pressaoMaxima;
	private BigDecimal pressaoMinima;
	
	public PressaoArterial() {
		super(LocalDateTime.now());
	}

	public PressaoArterial(BigDecimal pressaoMaxima, BigDecimal pressaoMinima, LocalDateTime dataRegistro) {
		super(dataRegistro);
		this.pressaoMaxima = pressaoMaxima;
		this.pressaoMinima = pressaoMinima;
	}
	
	public BigDecimal getPressaoMaxima() {
		return pressaoMaxima;
	}
	public void setPressaoMaxima(BigDecimal pressaoMaxima) {
		this.pressaoMaxima = pressaoMaxima;
	}
	public BigDecimal getPressaoMinima() {
		return pressaoMinima;
	}
	public void setPressaoMinima(BigDecimal pressaoMinima) {
		this.pressaoMinima = pressaoMinima;
	}

	@Override
	public void setDataRegistro(LocalDateTime dataRegistro) {
		super.setData(dataRegistro);
	}

	@Override
	public LocalDateTime getDataRegistro() {
		return super.getData();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((pressaoMaxima == null) ? 0 : pressaoMaxima.hashCode());
		result = prime * result + ((pressaoMinima == null) ? 0 : pressaoMinima.hashCode());
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
		PressaoArterial other = (PressaoArterial) obj;
		if (pressaoMaxima == null) {
			if (other.pressaoMaxima != null)
				return false;
		} else if (!pressaoMaxima.equals(other.pressaoMaxima))
			return false;
		if (pressaoMinima == null) {
			if (other.pressaoMinima != null)
				return false;
		} else if (!pressaoMinima.equals(other.pressaoMinima))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PressaoArterial [pressaoMaxima=" + pressaoMaxima + ", pressaoMinima=" + pressaoMinima + 
				", dataRegistro=" + getData() + "]";
	}
	
}
