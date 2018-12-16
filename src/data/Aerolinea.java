package data;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class Aerolinea {
	
	private int idAerolinea;
	private String nombreAerolinea;
	
	public Aerolinea(String nombreAerolinea) {
		super();
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

	@Override
	public String toString() {
		return "Aerolinea [idAerolinea=" + idAerolinea + ", nombreAerolinea=" + nombreAerolinea + "]";
	}
	
	
	
}
