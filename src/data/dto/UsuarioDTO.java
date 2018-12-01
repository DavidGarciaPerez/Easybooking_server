package data.dto;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDTO {
	
	private String nombre;
	private String email;
	private String sistemaAutentificacion;
	private List<ReservaDTO> reservas = new ArrayList<>();
	
	public UsuarioDTO(String nombre, String email, String sistemaAutentificacion) {
		super();
		this.nombre = nombre;
		this.email = email;
		this.sistemaAutentificacion = sistemaAutentificacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSistemaAutentificacion() {
		return sistemaAutentificacion;
	}

	public void setSistemaAutentificacion(String sistemaAutentificacion) {
		this.sistemaAutentificacion = sistemaAutentificacion;
	}
	
	public List<ReservaDTO> getReservas() {
		return reservas;
	}

	public void setReservas(List<ReservaDTO> reservas) {
		this.reservas = reservas;
	}
	
	public boolean isEmpty() {
		return reservas.isEmpty();
	}

	public int size() {
		return reservas.size();
	}
}
