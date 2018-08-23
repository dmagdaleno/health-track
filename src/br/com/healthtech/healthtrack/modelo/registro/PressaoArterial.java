package br.com.healthtech.healthtrack.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Abstrai informações sobre um registro de Pressão Arterial
 * @author dmagdaleno
 *
 */
public class PressaoArterial {
	private BigDecimal pressaoMaxima;
	private BigDecimal pressaoMinima;
	private LocalDateTime dataMedida;
	
	public PressaoArterial() {
	}

	public PressaoArterial(BigDecimal pressaoMaxima, BigDecimal pressaoMinima, LocalDateTime dataMedida) {
		this.pressaoMaxima = pressaoMaxima;
		this.pressaoMinima = pressaoMinima;
		this.dataMedida = dataMedida;
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
	public LocalDateTime getDataMedida() {
		return dataMedida;
	}
	public void setDataMedida(LocalDateTime dataMedida) {
		this.dataMedida = dataMedida;
	}
	
	@Override
	public String toString() {
		return "{ pressaoMaxima=" + pressaoMaxima + 
				", pressaoMinima=" + pressaoMinima + 
				", dataMedida=" + dataMedida + " }";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataMedida == null) ? 0 : dataMedida.hashCode());
		result = prime * result + ((pressaoMaxima == null) ? 0 : pressaoMaxima.hashCode());
		result = prime * result + ((pressaoMinima == null) ? 0 : pressaoMinima.hashCode());
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
		PressaoArterial other = (PressaoArterial) obj;
		if (dataMedida == null) {
			if (other.dataMedida != null)
				return false;
		} else if (!dataMedida.equals(other.dataMedida))
			return false;
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
}
