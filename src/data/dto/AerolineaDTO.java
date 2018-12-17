package data.dto;

import java.io.Serializable;

public class AerolineaDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombreAerolinea;
	
	public AerolineaDTO(String nombreAerolinea) {
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
