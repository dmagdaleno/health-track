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
	private Long id;
	private TipoAtividadeFisica tipo;
	private String descricao;
	private BigDecimal gastoCalorico;

	public AtividadeFisica(final Usuario usuario, final LocalDateTime dataRegistro) {
		super(usuario, dataRegistro);
	}

	public AtividadeFisica(Long id, TipoAtividadeFisica tipo, String descricao, BigDecimal gastoCalorico,
			LocalDateTime dataRegistro, Usuario usuario) {
		super(usuario, dataRegistro);
		this.id = id;
		this.tipo = tipo;
		this.descricao = descricao;
		this.gastoCalorico = gastoCalorico;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoAtividadeFisica getTipo() {
		return tipo;
	}

	public void setTipo(TipoAtividadeFisica tipo) {
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
				", gastoCalorico=" + gastoCalorico + ", usuario=" + usuario + 
				", dataRegistro=" + data + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((gastoCalorico == null) ? 0 : gastoCalorico.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (tipo != other.tipo)
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}
}
