package br.com.healthtech.healthtrack.modelo.registro;

import java.time.LocalDateTime;

import br.com.healthtech.healthtrack.modelo.Usuario;

/**
 * Classe abstrata que representa um registro genérico 
 * associado com um usuário e uma data
 * 
 * @author dmagdaleno
 *
 */
public abstract class Registro {
	protected final Long id;
	protected final Usuario usuario;
	protected final LocalDateTime data;
	
	/**
	 * Recupera id do registro.
	 * 
	 * @return
	 * 	objeto do tipo {@link Long} que representa o id do registro
	 */
	public abstract Long getId();
	
	/**
	 * Recupera usuario a quem o registro se refere.
	 * 
	 * @return
	 * 	objeto do tipo {@link Usuario} associado ao registro
	 */
	public abstract Usuario getUsuario();
	
	/**
	 * Recupera a data e hora em que o registro foi realizado.
	 * 
	 * @return
	 * 	objeto do tipo {@link LocalDateTime} associado ao registro
	 */
	public abstract LocalDateTime getDataRegistro();
	
	protected Registro(final Long id, final Usuario usuario, final LocalDateTime data){
		this.id = id;
		this.usuario = usuario;
		this.data = data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		Registro other = (Registro) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}
	
}
