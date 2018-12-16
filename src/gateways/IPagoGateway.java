package gateways;

public interface IPagoGateway {
	public boolean realizarPago(String source, String target, double importe, String concepto);
}
