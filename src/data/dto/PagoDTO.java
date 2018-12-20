package data.dto;

import java.io.Serializable;

public class PagoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PaypalDTO paypal;
	private CreditcardDTO creditcard;
	private double importe;
	private String concepto;

	public PagoDTO(PaypalDTO paypal, CreditcardDTO creditcard, double importe, String concepto) {
		super();
		this.importe = importe;
		this.concepto = concepto;
		this.paypal = paypal;
		this.creditcard = creditcard;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
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
