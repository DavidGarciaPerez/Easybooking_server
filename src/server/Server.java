package server;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import dao.DAOImplement;
import data.Creditcard;
import data.Paypal;
import data.Vuelo;
import gateways.FacebookGateway;
import gateways.GatewayFactory;
import gateways.GoogleGateway;
import gateways.PaypalGateway;
import gateways.RyanairGateway;
import gateways.VisaGateway;
import gateways.VuelingGateway;

public class Server {

	// private static final long serialVersionUID = 1L;// Serializable
	//
	//
	// // Constructor por defecto:
	// protected Server() throws RemoteException {
	// super();
	// }
	//
	// // Método main para invocar servidor:
	// public static void main(String[] args) throws RemoteException,
	// MalformedURLException{
	// if (args.length != 3) {
	// System.out.println("usage: java [policy] [codebase] server.Server [host]
	// [port] [server]");
	// System.exit(0);
	// }
	//
	// if (System.getSecurityManager() == null) {
	// System.setSecurityManager(new SecurityManager());
	// }
	//
	// String name = "//" + args[0] + ":" + args[1] + "/" + args[2];
	//
	// try {
	// IServer objServer = new Server();
	// Naming.rebind(name, objServer);
	// System.out.println("* Server '" + name + "' active and waiting...");
	// } catch (Exception e) {
	// System.err.println("- Exception running the server: " + e.getMessage());
	// e.printStackTrace();
	// }
	// }

	private PaypalGateway paypalGateway;
	private VisaGateway visaGateway;
	private RyanairGateway ryanairGateway;
	private VuelingGateway vuelingGateway;
	private GoogleGateway googleGateway;
	private FacebookGateway facebookGateway;

	Server() {
		this.paypalGateway = (PaypalGateway) GatewayFactory.getInstance().createPagoGateway("PAYPAL");
		this.visaGateway = (VisaGateway) GatewayFactory.getInstance().createPagoGateway("VISA");
		this.ryanairGateway = (RyanairGateway) GatewayFactory.getInstance().createAerolineaGateway("RYANAIR");
		this.vuelingGateway = (VuelingGateway) GatewayFactory.getInstance().createAerolineaGateway("VUELING");
		this.googleGateway = (GoogleGateway) GatewayFactory.getInstance().createConexionGateway("GOOGLE");
		this.facebookGateway = (FacebookGateway) GatewayFactory.getInstance().createConexionGateway("FACEBOOK");
	}

	public synchronized boolean login(String email, String sistemaAutentificacion) {
		boolean dev = false;
		// Dependiendo del sistema de autentificación hay que ir o por GOOGLE o por
		// FACEBOOK:
		if (sistemaAutentificacion.equalsIgnoreCase("GOOGLE")) {
			// Nos conectamos por google:
			// Accedemos al gateway de google para conectarnos y si todo va bien nos
			// devolverá un true:
			dev = this.googleGateway.login(email);
		} else if (sistemaAutentificacion.equalsIgnoreCase("FACEBOOK")) {
			// Nos conectamos por facebook:
			// Accedemos al gateway de facebook para conectanos y si todo va bien nos
			// devolverá un true:
			dev = this.facebookGateway.login(email);
		} else {
			// Error de parámetro:
			System.err.println("PARAM ERROR: " + sistemaAutentificacion);
		}
		return dev;
	}

	public synchronized boolean registerUser(String nombre, String email, String sistemaAutentificacion) {
		// Para registrar un nuevo usuario primero hay que comprobar si no existe ya en
		// nuesra base de datos:

		// Si no existe entonces procederemos al registro de los datos del usuario nuevo
		// en nuestra base de datos:
		return false;
	}

	public synchronized ArrayList<Vuelo> buscarVuelos(String origen, String destino, int nPlazas) {
		// Conectamos con los gateways de las aeroníneas para pedirles los vuelos:
		List<Vuelo> vuelosRyanair = this.ryanairGateway.buscarVuelo();
		List<Vuelo> vuelosVueling = this.vuelingGateway.buscarVuelo();
		// Cuando obtengamos los vuelos los procesamos con las condiciones de los
		// parámetros pasados al servidor y los
		// devolvemos en un único arrayList:
		ArrayList<Vuelo> listaADevolver = new ArrayList<Vuelo>();
		for (Vuelo vueloRyanair : vuelosRyanair) {
			if (vueloRyanair.getAeropuertoOrigen().getUbicacion().equalsIgnoreCase(origen)
					&& vueloRyanair.getAeropuertoDestino().getUbicacion().equalsIgnoreCase(destino)
					&& vueloRyanair.getAsientosLibres() >= nPlazas) {
				// Entonces podemos guardar el vuelo como válido en la lista:
				listaADevolver.add(vueloRyanair);
			}
		}
		// Ahora lo mismo para los vuelos de Vueling:
		for (Vuelo vueloVueling : vuelosVueling) {
			if (vueloVueling.getAeropuertoOrigen().getUbicacion().equalsIgnoreCase(origen)
					&& vueloVueling.getAeropuertoDestino().getUbicacion().equalsIgnoreCase(destino)
					&& vueloVueling.getAsientosLibres() >= nPlazas) {
				// Entonces podemos guardar el vuelo como válido en la lista:
				listaADevolver.add(vueloVueling);
			}
		}

		return listaADevolver;
	}

	public synchronized boolean realizarReserva(Vuelo vuelo, int nPlazas, String[] pasajeros) {
		// Conectamos con el gateway de la aerolínea: para eso hay que buscarlo en
		// "Vuelo":
		if (vuelo.getAerolinea().getNombreAerolinea().equalsIgnoreCase("VUELING")) {
			// Conectamos con VUELING:
			this.vuelingGateway.decrementarPlazasLibres(vuelo);
		} else {
			// Conectamos con RYANAIR:
			this.ryanairGateway.decrementarPlazasLibres(vuelo);
		}

		// Una vez decrementadas las plazas podemos guardar la reserva en la bd:
		return DAOImplement.getInstance().realizarReserva(vuelo);
	}

	public synchronized boolean realizarPagoPaypal(Paypal paypalOrigen, Paypal paypalDestino, double importe,
			String concepto) {
		boolean dev = false;
		// Conectamos con el gateway de paypal para pagar:
		if (this.paypalGateway.realizarPago(paypalOrigen.getNombreUsuario(), paypalDestino.getNombreUsuario(), importe,
				concepto)) {
			dev = true;
			// Si el pago ha ido correctamente entonces guardamos la transacción en nuestra
			// base de datos:
			if (dev) {

			}
		}

		return dev;
	}

	public synchronized boolean realizarPagoCreditCard(Creditcard creditcardOrigen, Creditcard creditcardDestino,
			double importe, String concepto) {
		boolean dev = false;
		// Conectamos con el gateway de visa para pagar:
		if (this.visaGateway.realizarPago(creditcardOrigen.getNumeroTarjeta(), creditcardDestino.getNumeroTarjeta(),
				importe, concepto)) {
			dev = true;
			// Si el pago ha ido correctamente entonces guardamos la transacción en nuestra
			// base de datos:
			if (dev) {

			}
		}
		return dev;
	}

	// public synchronized boolean realizarReserva(Reserva reservaARealizar, int
	// nPlazas, String[] pasajeros)
	// throws RemoteException {
	// // TODO Auto-generated method stub
	// return false;
	// }

}