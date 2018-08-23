package br.com.healthtech.healthtrack.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Abstrai informações sobre um registro de Alimentação
 * @author dmagdaleno
 *
 */
public class Alimentacao {
	private Long id;
	private TipoAlimentacao tipo;
	private String descricao;
	private BigDecimal valorCalorico;
	private LocalDateTime data;
	private Usuario usuario;
	
	public Alimentacao() {
	}

	public Alimentacao(Long id, TipoAlimentacao tipo, String descricao, BigDecimal valorCalorico, 
			LocalDateTime data, Usuario usuario) {
		this.id = id;
		this.tipo = tipo;
		this.descricao = descricao;
		this.valorCalorico = valorCalorico;
		this.data = data;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoAlimentacao getTipo() {
		return tipo;
	}

	public void setTipo(TipoAlimentacao tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValorCalorico() {
		return valorCalorico;
	}

	public void setValorCalorico(BigDecimal valorCalorico) {
		this.valorCalorico = valorCalorico;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "{ id=" + id + ", tipo=" + tipo + ", descricao=" + descricao + ", valorCalorico="
				+ valorCalorico + ", data=" + data + ", usuario=" + usuario + " }";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		result = prime * result + ((valorCalorico == null) ? 0 : valorCalorico.hashCode());
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
		Alimentacao other = (Alimentacao) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
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
		if (valorCalorico == null) {
			if (other.valorCalorico != null)
				return false;
		} else if (!valorCalorico.equals(other.valorCalorico))
			return false;
		return true;
	}
}
