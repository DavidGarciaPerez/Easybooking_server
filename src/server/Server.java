package server;

import java.util.ArrayList;
import java.util.List;

import dao.DAOImplement;
import data.Creditcard;
import data.Paypal;
import data.Reserva;
import data.Usuario;
import data.Vuelo;
import gateways.FacebookGateway;
import gateways.GatewayFactory;
import gateways.GoogleGateway;
import gateways.PaypalGateway;
import gateways.RyanairGateway;
import gateways.VisaGateway;
import gateways.VuelingGateway;

public class Server {

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
		// Dependiendo del sistema de autentificaci�n hay que ir o por GOOGLE o por
		// FACEBOOK:
		if (sistemaAutentificacion.equalsIgnoreCase("GOOGLE")) {
			// Nos conectamos por google:
			// Accedemos al gateway de google para conectarnos y si todo va bien nos
			// devolver� un true:
			dev = this.googleGateway.login(email);
		} else if (sistemaAutentificacion.equalsIgnoreCase("FACEBOOK")) {
			// Nos conectamos por facebook:
			// Accedemos al gateway de facebook para conectanos y si todo va bien nos
			// devolver� un true:
			dev = this.facebookGateway.login(email);
		} else {
			// Error de par�metro:
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
		// Conectamos con los gateways de las aeron�neas para pedirles los vuelos:
		List<Vuelo> vuelosRyanair = this.ryanairGateway.buscarVuelo();
		List<Vuelo> vuelosVueling = this.vuelingGateway.buscarVuelo();
		// Cuando obtengamos los vuelos los procesamos con las condiciones de los
		// par�metros pasados al servidor y los
		// devolvemos en un �nico arrayList:
		ArrayList<Vuelo> listaADevolver = new ArrayList<Vuelo>();
		for (Vuelo vueloRyanair : vuelosRyanair) {
			if (vueloRyanair.getAeropuertoOrigen().getUbicacion().equalsIgnoreCase(origen)
					&& vueloRyanair.getAeropuertoDestino().getUbicacion().equalsIgnoreCase(destino)
					&& vueloRyanair.getAsientosLibres() >= nPlazas) {
				// Entonces podemos guardar el vuelo como v�lido en la lista:
				listaADevolver.add(vueloRyanair);
			}
		}
		// Ahora lo mismo para los vuelos de Vueling:
		for (Vuelo vueloVueling : vuelosVueling) {
			if (vueloVueling.getAeropuertoOrigen().getUbicacion().equalsIgnoreCase(origen)
					&& vueloVueling.getAeropuertoDestino().getUbicacion().equalsIgnoreCase(destino)
					&& vueloVueling.getAsientosLibres() >= nPlazas) {
				// Entonces podemos guardar el vuelo como v�lido en la lista:
				listaADevolver.add(vueloVueling);
			}
		}

		return listaADevolver;
	}

	public synchronized boolean realizarReserva(Reserva reservaARealizar, int nPlazas, String[] pasajeros) {
		// Conectamos con el gateway de la aerol�nea: para eso hay que buscarlo en
		// "Vuelo":
		if (reservaARealizar.getVuelo().getAerolinea().getNombreAerolinea().equalsIgnoreCase("VUELING")) {
			// Conectamos con VUELING:
			this.vuelingGateway.decrementarPlazasLibres(reservaARealizar.getVuelo());
		} else {
			// Conectamos con RYANAIR:
			this.ryanairGateway.decrementarPlazasLibres(reservaARealizar.getVuelo());
		}

		// Una vez decrementadas las plazas podemos guardar la reserva en la bd:
		return DAOImplement.getInstance().realizarReserva(reservaARealizar);
	}

	public synchronized boolean realizarPagoPaypal(Paypal paypalOrigen, Paypal paypalDestino, double importe,
			String concepto) {
		boolean dev = false;
		// Conectamos con el gateway de paypal para pagar:
		if (this.paypalGateway.realizarPago(paypalOrigen.getNombreUsuario(), paypalDestino.getNombreUsuario(), importe,
				concepto)) {
			dev = true;
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
		}
		return dev;
	}

	public synchronized List<Reserva> getReservas(Usuario usuario) {
		return DAOImplement.getInstance().getReservas(usuario);
	}

	// public synchronized boolean realizarReserva(Reserva reservaARealizar, int
	// nPlazas, String[] pasajeros)
	// throws RemoteException {
	// // TODO Auto-generated method stub
	// return false;
	// }

}