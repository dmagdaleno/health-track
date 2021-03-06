package br.com.healthtech.healthtrack.modelo.registro;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.healthtech.healthtrack.modelo.Usuario;

/**
 * Abstrai informações sobre um registro de Atividade Física
 * @author dmagdaleno
 *
 */
public class AtividadeFisica extends Registro {
	private Tipo tipo;
	private String descricao;
	private BigDecimal gastoCalorico;
	
	public AtividadeFisica(Tipo tipo, String descricao, BigDecimal gastoCalorico,
			LocalDateTime dataRegistro, Usuario usuario) {
		this(null, tipo, descricao, gastoCalorico, dataRegistro, usuario);
	}

	public AtividadeFisica(Long id, Tipo tipo, String descricao, BigDecimal gastoCalorico,
			LocalDateTime dataRegistro, Usuario usuario) {
		super(id, dataRegistro, usuario);
		this.tipo = tipo;
		this.descricao = descricao;
		this.gastoCalorico = gastoCalorico;
	}

	public AtividadeFisica(Long id, int idTipo, String descricao, BigDecimal gastoCalorico,
			LocalDateTime dataRegistro, Long idUsuario) {
		this(id, new Tipo(idTipo), descricao, gastoCalorico, dataRegistro, new Usuario(idUsuario));
	}
	
	public AtividadeFisica(int idTipo, String descricao, BigDecimal gastoCalorico,
			LocalDateTime dataRegistro, Long idUsuario) {
		this(null, new Tipo(idTipo), descricao, gastoCalorico, dataRegistro, new Usuario(idUsuario));
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getGastoCalorico() {
		return gastoCalorico;
	}

	public void setGastoCalorico(BigDecimal gastoCalorico) {
		this.gastoCalorico = gastoCalorico;
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
		return "AtividadeFisica [id=" + id + ", tipo=" + tipo + ", descricao=" + descricao + 
				", gastoCalorico=" + gastoCalorico + ", usuario=[id=" + usuario.getId() + "]" + 
				", dataRegistro=" + data + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((gastoCalorico == null) ? 0 : gastoCalorico.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		AtividadeFisica other = (AtividadeFisica) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (gastoCalorico == null) {
			if (other.gastoCalorico != null)
				return false;
		} else if (!gastoCalorico.equals(other.gastoCalorico))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}
}