package data;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Pago {
	
	@PrimaryKey
	private int idPago;
	private Paypal paypal;
	private Creditcard creditcard;
	

	public Pago(Paypal paypal, Creditcard creditcard) {
		super();
		this.paypal = paypal;
		this.creditcard = creditcard;
	}

	public int getIdPago() {
		return idPago;
	}

	public void setIdPago(int idPago) {
		this.idPago = idPago;
	}

	public Paypal getPaypal() {
		return paypal;
	}

	public void setPaypal(Paypal paypal) {
		this.paypal = paypal;
	}

	public Creditcard getCreditcard() {
		return creditcard;
	}

	public void setCreditcard(Creditcard creditcard) {
		this.creditcard = creditcard;
	}

}
