package br.com.healthtech.healthtrack.modelo.registro;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.healthtech.healthtrack.modelo.Usuario;

/**
 * Abstrai informações sobre um registro de Alimentação
 * @author dmagdaleno
 *
 */
public class Alimentacao extends Registro {
	private Long id;
	private TipoAlimentacao tipo;
	private String descricao;
	private BigDecimal valorCalorico;
	private Usuario usuario;
	
	public Alimentacao() {
		super(LocalDateTime.now());
	}

	public Alimentacao(Long id, TipoAlimentacao tipo, String descricao, BigDecimal valorCalorico, 
			LocalDateTime dataRegistro, Usuario usuario) {
		super(dataRegistro);
		
		this.id = id;
		this.tipo = tipo;
		this.descricao = descricao;
		this.valorCalorico = valorCalorico;
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
	
	@Override
	public LocalDateTime getDataRegistro() {
		return super.getData();
	}

	@Override
	public void setDataRegistro(LocalDateTime dataRegistro) {
		super.setData(dataRegistro);;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	

	@Override
	public String toString() {
		return "Alimentacao [id=" + id + ", tipo=" + tipo + ", descricao=" + descricao + 
				", valorCalorico=" + valorCalorico + ", usuario=" + usuario + 
				", dataRegistro=" + super.getData() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
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
