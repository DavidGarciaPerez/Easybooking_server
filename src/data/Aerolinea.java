package data;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class Aerolinea {

	private String nombreAerolinea;

	public Aerolinea(String nombreAerolinea) {
		super();
		this.nombreAerolinea = nombreAerolinea;
	}

	public String getNombreAerolinea() {
		return nombreAerolinea;
	}

	public void setNombreAerolinea(String nombreAerolinea) {
		this.nombreAerolinea = nombreAerolinea;
	}
}
