package data.dto;

public class PagoDTO {

	private PaypalDTO paypal;
	private CreditcardDTO creditcard;

	public PagoDTO(PaypalDTO paypal, CreditcardDTO creditcard) {
		super();
		this.paypal = paypal;
		this.creditcard = creditcard;
	}

	public PaypalDTO getPaypal() {
		return paypal;
	}

	public void setPaypal(PaypalDTO paypal) {
		this.paypal = paypal;
	}

	public CreditcardDTO getCreditcard() {
		return creditcard;
	}

	public void setCreditcard(CreditcardDTO creditcard) {
		this.creditcard = creditcard;
	}
}
