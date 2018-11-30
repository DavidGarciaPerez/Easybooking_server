package data.dto;

public class AerolineaDTO {

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
