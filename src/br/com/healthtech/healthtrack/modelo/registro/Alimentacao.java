package br.com.healthtech.healthtrack.modelo.registro;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.healthtech.healthtrack.modelo.Usuario;

/**
 * Abstrai informações sobre um registro de AlimentaÃ§Ã£o
 * @author dmagdaleno
 *
 */
public class Alimentacao extends Registro {
	private Tipo tipo;
	private String descricao;
	private BigDecimal valorCalorico;
	
	public Alimentacao(Tipo tipo, String descricao, BigDecimal valorCalorico, 
			LocalDateTime dataRegistro, Usuario usuario) {
		
		this(null, tipo, descricao, valorCalorico, dataRegistro, usuario);
	}

	public Alimentacao(Long id, Tipo tipo, String descricao, BigDecimal valorCalorico, 
			LocalDateTime dataRegistro, Usuario usuario) {
		super(id, dataRegistro, usuario);
		
		this.tipo = tipo;
		this.descricao = descricao;
		this.valorCalorico = valorCalorico;
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

	public BigDecimal getValorCalorico() {
		return valorCalorico;
	}

	public void setValorCalorico(BigDecimal valorCalorico) {
		this.valorCalorico = valorCalorico;
	}
	
	@Override
	public Long getId() {
		return id;
	}
	
	@Override
	public LocalDateTime getDataRegistro() {
		return data;
	}
	
	@Override
	public Usuario getUsuario() {
		return usuario;
	}

	@Override
	public String toString() {
		return "Alimentacao [id=" + id + ", tipo=" + tipo + ", descricao=" + descricao + 
				", valorCalorico=" + valorCalorico + ", usuario=[id=" + usuario.getId() + "]" + 
				", dataRegistro=" + data + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		result = prime * result + ((valorCalorico == null) ? 0 : valorCalorico.hashCode());
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
		Alimentacao other = (Alimentacao) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		if (valorCalorico == null) {
			if (other.valorCalorico != null)
				return false;
		} else if (!valorCalorico.equals(other.valorCalorico))
			return false;
		return true;
	}
}
