package data.dto;

public class ReservaDTO {

	private double precio;
	private String asiento;
	private UsuarioDTO usuario;
	private VueloDTO vuelo;
	private PagoDTO pago;
	
	public ReservaDTO(double precio, String asiento, UsuarioDTO usuario, VueloDTO vuelo, PagoDTO pago) {
		super();
		this.precio = precio;
		this.asiento = asiento;
		this.usuario = usuario;
		this.vuelo = vuelo;
		this.pago = pago;
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
	public UsuarioDTO getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}
	public VueloDTO getVuelo() {
		return vuelo;
	}
	public void setVuelo(VueloDTO vuelo) {
		this.vuelo = vuelo;
	}
	public PagoDTO getPago() {
		return pago;
	}
	public void setPago(PagoDTO pago) {
		this.pago = pago;
	}
	
}
