package data.dto;

import java.io.Serializable;

public class AeropuertoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String ubicacion;

	public AeropuertoDTO(String nombre, String ubicacion) {
		super();
		this.nombre = nombre;
		this.ubicacion = ubicacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
}
