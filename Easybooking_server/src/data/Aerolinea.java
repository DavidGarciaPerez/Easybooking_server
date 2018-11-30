package data;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class Aerolinea {
	
	private int idAerolinea;
	private String nombreAerolinea;
	
	public Aerolinea(int idAerolinea, String nombreAerolinea) {
		super();
		this.idAerolinea = idAerolinea;
		this.nombreAerolinea = nombreAerolinea;
	}

	public int getIdAerolinea() {
		return idAerolinea;
	}
	public void setIdAerolinea(int idAerolinea) {
		this.idAerolinea = idAerolinea;
	}
	public String getNombreAerolinea() {
		return nombreAerolinea;
	}
	public void setNombreAerolinea(String nombreAerolinea) {
		this.nombreAerolinea = nombreAerolinea;
	}
	
}
