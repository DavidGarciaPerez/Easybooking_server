package clases;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;


@PersistenceCapable
public class Paypal {

	@PrimaryKey
	private int idPaypal;
	private String nombreUsuario;

	public int getIdPaypal() {
		return idPaypal;
	}

	public void setIdPaypal(int idPaypal) {
		this.idPaypal = idPaypal;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

}
