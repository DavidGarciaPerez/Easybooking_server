package data.dto;

import java.io.Serializable;
import java.util.Date;

public class CreditcardDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String numeroTarjeta;
	private int cvv2;
	private Date fechaCaducidad;

	public CreditcardDTO(String numeroTarjeta, int cvv2, Date fechaCaducidad) {
		super();
		this.numeroTarjeta = numeroTarjeta;
		this.cvv2 = cvv2;
		this.fechaCaducidad = fechaCaducidad;
	}

	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public int getCvv2() {
		return cvv2;
	}

	public void setCvv2(int cvv2) {
		this.cvv2 = cvv2;
	}

	public Date getFechaCaducidad() {
		return fechaCaducidad;
	}

	public void setFechaCaducidad(Date fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}
	
}
