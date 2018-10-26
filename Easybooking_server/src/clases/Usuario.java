package clases;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Usuario {
	
	@PrimaryKey
	private String nombre;
	private String email;
	private String sistemaAutentificacion;
	
	@Join
	@Persistent(mappedBy="usuario", dependentElement="true")
	private List<Reserva> reservas = new ArrayList<>();

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

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}
	
	public boolean isEmpty() {
		return reservas.isEmpty();
	}

	public int size() {
		return reservas.size();
	}
}
