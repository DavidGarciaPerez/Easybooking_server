package data;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Reserva {

	@PrimaryKey
	private int idReserva;
	private double precio;
	private String asiento;
	private Usuario usuario;
	private Vuelo vuelo;
	private Pago pago;
	
	public Reserva(double precio, String asiento, Usuario usuario, Vuelo vuelo, Pago pago) {
		super();
		this.precio = precio;
		this.asiento = asiento;
		this.usuario = usuario;
		this.vuelo = vuelo;
		this.pago = pago;
	}
	
	public int getIdReserva() {
		return idReserva;
	}
	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public String getAsiento() {
		return asiento;
	}
	public void setAsiento(String asiento) {
		this.asiento = asiento;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Vuelo getVuelo() {
		return vuelo;
	}
	public void setVuelo(Vuelo vuelo) {
		this.vuelo = vuelo;
	}
	public Pago getPago() {
		return pago;
	}
	public void setPago(Pago pago) {
		this.pago = pago;
	}
	
	
	
}
