package br.com.healthtech.healthtrack.modelo.registro;

import java.time.LocalDateTime;

public abstract class Registro {
	private LocalDateTime data;
	
	public abstract void setDataRegistro(LocalDateTime data);
	public abstract LocalDateTime getDataRegistro();
	
	protected Registro(LocalDateTime data){
		this.data = data;
	}

	protected LocalDateTime getData() {
		return data;
	}

	protected void setData(LocalDateTime data) {
		this.data = data;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
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
		return true;
	}
	
	

}
