package services;

import java.rmi.Remote;
import java.rmi.RemoteException;

import data.dto.CreditcardDTO;
import data.dto.PaypalDTO;

public interface IPagoService  extends Remote{
	
	public boolean realizarPagoPaypal(PaypalDTO paypalOrigen, PaypalDTO paypalDestino, double importe, String concepto) throws RemoteException;
	public boolean realizarPagoCreditCard(CreditcardDTO creditcardOrigen, CreditcardDTO creditcardDestino, double importe, String concepto) throws RemoteException;

}
