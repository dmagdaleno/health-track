package br.com.healthtech.healthtrack.modelo.registro;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.healthtech.healthtrack.modelo.Usuario;

/**
 * Abstrai informações sobre um registro de Peso
 * @author dmagdaleno
 *
 */
public class Peso extends Registro {
	private BigDecimal peso;
	
	public Peso(BigDecimal peso, LocalDateTime dataRegistro, final Usuario usuario) {
		this(null, peso, dataRegistro, usuario);
	}
	
	public Peso(final Long id, BigDecimal peso, LocalDateTime dataRegistro, final Usuario usuario) {
		super(id, dataRegistro, usuario);
		this.peso = peso.setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}
	
	public Peso(BigDecimal peso, LocalDateTime dataRegistro, final Long idUsuario) {
		this(peso, dataRegistro, new Usuario(1L));
	}

	public BigDecimal getPeso() {
		return peso;
	}
	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}
	
	@Override
	public Long getId() {
		return id;
	}
	
	@Override
	public Usuario getUsuario() {
		return usuario;
	}

	@Override
	public LocalDateTime getDataRegistro() {
		return data;
	}

	@Override
	public String toString() {
		return "Peso [peso=" + peso + ", id=" + id + ", usuario=[id=" + usuario.getId() + "]" + ", data=" + data + "]";
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
