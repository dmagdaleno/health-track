package br.com.healthtech.healthtrack.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Abstrai informações sobre o usuário da aplicação
 * @author dmagdaleno
 *
 */
public class Usuario {
	private Long id;
	private String nome;
	private LocalDate dataNascimento;
	private String genero;
	private BigDecimal altura;
	private BigDecimal limiteCaloria;
	
	public Usuario() {
	}
	
	public Usuario(Long id) {
		this.id = id;
	}

	public Usuario(Long id, String nome, LocalDate dataNascimento, String genero, BigDecimal altura, BigDecimal limiteCaloria) {
		this.id = id;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.genero = genero;
		this.altura = altura.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		this.limiteCaloria = limiteCaloria;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public BigDecimal getAltura() {
		return altura;
	}

	public void setAltura(BigDecimal altura) {
		this.altura = altura;
	}

	public BigDecimal getLimiteCaloria() {
		return limiteCaloria;
	}

	public void setLimiteCaloria(BigDecimal limiteCaloria) {
		this.limiteCaloria = limiteCaloria;
	}
	
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", dataNascimento=" + dataNascimento + ", genero=" + genero
				+ ", altura=" + altura + ", limiteCaloria=" + limiteCaloria + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((altura == null) ? 0 : altura.hashCode());
		result = prime * result + ((dataNascimento == null) ? 0 : dataNascimento.hashCode());
		result = prime * result + ((genero == null) ? 0 : genero.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((limiteCaloria == null) ? 0 : limiteCaloria.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Usuario other = (Usuario) obj;
		if (altura == null) {
			if (other.altura != null)
				return false;
		} else if (!altura.equals(other.altura))
			return false;
		if (dataNascimento == null) {
			if (other.dataNascimento != null)
				return false;
		} else if (!dataNascimento.equals(other.dataNascimento))
			return false;
		if (genero == null) {
			if (other.genero != null)
				return false;
		} else if (!genero.equals(other.genero))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (limiteCaloria == null) {
			if (other.limiteCaloria != null)
				return false;
		} else if (!limiteCaloria.equals(other.limiteCaloria))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

}
