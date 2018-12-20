package services;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import data.Creditcard;
import data.Paypal;
import data.dto.CreditcardDTO;
import data.dto.PaypalDTO;
import server.Server;

public class PagoService extends UnicastRemoteObject implements IPagoService {

	private static final long serialVersionUID = 1L;
	private Server server;

	public PagoService(Server server) throws RemoteException {
		super();
		this.server = server;
	}

	@Override
	public boolean realizarPagoPaypal(PaypalDTO paypalOrigen, PaypalDTO paypalDestino, double importe, String concepto)
			throws RemoteException {
		// TODO Auto-generated method stub
		Paypal paypalOrigenDATA = new Paypal(paypalOrigen.getNombreUsuario());
		Paypal paypalDestinoDATA = new Paypal(paypalDestino.getNombreUsuario());
		return this.server.realizarPagoPaypal(paypalOrigenDATA, paypalDestinoDATA, importe, concepto);
	}

	@Override
	public boolean realizarPagoCreditCard(CreditcardDTO creditcardOrigen, CreditcardDTO creditcardDestino,
			double importe, String concepto) throws RemoteException {
		// TODO Auto-generated method stub
		Creditcard creditcardOrigenDATA = new Creditcard(creditcardOrigen.getNumeroTarjeta(),
				creditcardOrigen.getCvv2(), creditcardOrigen.getFechaCaducidad());
		Creditcard creditcardDestinoDATA = new Creditcard(creditcardDestino.getNumeroTarjeta(),
				creditcardDestino.getCvv2(), creditcardDestino.getFechaCaducidad());
		return this.server.realizarPagoCreditCard(creditcardOrigenDATA, creditcardDestinoDATA, importe, concepto);
	}

}
