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
	
	public Peso(final Long id, final Usuario usuario, final LocalDateTime dataRegistro) {
		super(id, usuario, dataRegistro);
	}
	
	public Peso(final Long id, BigDecimal peso, LocalDateTime dataRegistro, final Usuario usuario) {
		super(id, usuario, dataRegistro);
		this.peso = peso;
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
	
}
