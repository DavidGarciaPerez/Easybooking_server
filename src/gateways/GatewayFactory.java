package gateways;

public class GatewayFactory {

	private static GatewayFactory instance = new GatewayFactory();

	private GatewayFactory() {
	}

	public static GatewayFactory getInstance() {
		return instance;
	}

	public IAerolineaGateway createAerolineaGateway(String type) {
		if (type == null) {
			return null;
		}

		if (type.equalsIgnoreCase("VUELING")) {
			return new VuelingGateway();
		} else if (type.equalsIgnoreCase("RYANAIR")) {
			return new RyanairGateway();
		} else {
			return null;
		}

	}

	public IConexionGateway createConexionGateway(String type) {
		if (type == null) {
			return null;
		}

		if (type.equalsIgnoreCase("GOOGLE")) {
			return new GoogleGateway();
		} else if (type.equalsIgnoreCase("FACEBOOK")) {
			return new FacebookGateway();
		} else {
			return null;
		}

	}

	public IPagoGateway createPagoGateway(String type) {
		if (type == null) {
			return null;
		}

		if (type.equalsIgnoreCase("VISA")) {
			return new VisaGateway();
		} else if (type.equalsIgnoreCase("PAYPAL")) {
			return new PaypalGateway();
		} else {
			return null;
		}

	}
}
