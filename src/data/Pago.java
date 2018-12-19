package data;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Pago {
	
	private Paypal paypal;
	private Creditcard creditcard;
	

	public Pago(Paypal paypal, Creditcard creditcard) {
		super();
		this.paypal = paypal;
		this.creditcard = creditcard;
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
