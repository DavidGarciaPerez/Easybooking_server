package data;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class Aeropuerto {

	private String nombre;
	private String ubicacion;

	public Aeropuerto(String nombre, String ubicacion) {
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
