package data.dto;

import java.io.Serializable;

public class PaypalDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombreUsuario;

	public PaypalDTO(String nombreUsuario) {
		super();
		this.nombreUsuario = nombreUsuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
}
