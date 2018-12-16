package gateways;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import data.Aerolinea;
import data.Aeropuerto;
import data.Vuelo;

public class RyanairGateway implements IAerolineaGateway {

	private ArrayList<Aeropuerto> aeropuertos = generarAeropuertos();
	private int sizeAeropuertos = this.aeropuertos.size();

	@Override
	public ArrayList<Vuelo> buscarVuelo() {
		// TODO Auto-generated method stub
		return generarListVuelos();
	}

	// M�todo para generrar una lista de vuelos, de esta forma estamos simulando que
	// el servicio de Vueling nos proporcione sus vuelos...
	private ArrayList<Vuelo> generarListVuelos() {
		ArrayList<Vuelo> vuelos = new ArrayList<Vuelo>();

		// Crearemos una lista de un total de 100 vuelos como m�ximo:
		for (int i = 0; i < 100; i++) {
			// (int numVuelo, Date horaSalida, Date horaLlegada, int numAsientos, int
			// asientosLibres,
			// Aeropuerto aeropuertoOrigen, Aeropuerto aeropuertoDestino, Aerolinea
			// aerolinea)

			int numVuelo = 1000 + i;
			Date horaSalida = generarRandomDate(new Date(System.currentTimeMillis()),
					new Date(System.currentTimeMillis() + new Random().nextInt(9999999 - 999999 + 1) + 999999));
			Date horaLlegada = generarRandomDate(new Date(System.currentTimeMillis()),
					new Date(System.currentTimeMillis() + new Random().nextInt(99999999 - 9999999 + 1) + 9999999));
			int numAsientos = new Random().nextInt(350 - 250 + 1) + 250; // 350 = maximo, 250 = min
			int asientosLibres = new Random().nextInt(numAsientos - 0 + 1) + 0;

			int randAO = new Random().nextInt((this.sizeAeropuertos - 1) - (this.sizeAeropuertos - 1) / 2 + 1)
					+ (this.sizeAeropuertos - 1) / 2;
			int randAD = new Random().nextInt((this.sizeAeropuertos - 1) / 2 - 0 + 1) + 0;

			Aeropuerto aeropuertoOrigen = this.aeropuertos.get(randAO);
			Aeropuerto aeropuertoDestino = this.aeropuertos.get(randAD);

			Aerolinea aerolinea = new Aerolinea("RYANAIR");

			// Una vez creados los par�metros necesarios de modo casi aleatorio ya podemos
			// guardarlos en la lista:
			vuelos.add(new Vuelo(numVuelo, horaSalida, horaLlegada, numAsientos, asientosLibres, aeropuertoOrigen,
					aeropuertoDestino, aerolinea));

		}

		return vuelos;
	}

	private Date generarRandomDate(Date d1, Date d2) {
		return new Date(ThreadLocalRandom.current().nextLong(d1.getTime(), d2.getTime()));
	}

	private ArrayList<Aeropuerto> generarAeropuertos() {
		ArrayList<Aeropuerto> lista = new ArrayList<Aeropuerto>();
		String[] nombresAeropuertos = { "Aeropuerto de Berl�n-Sch�nefeld", "Aeropuerto de Berl�n-Tegel",
				"Aeropuerto de Berl�n-Tempelhof", "Aeropuerto de Bremen", "Aeropuerto de Brunswick/Wolfsburg",
				"Aeropuerto de Colonia/Bonn", "Aeropuerto de Dortmund", "Aeropuerto de Dresde",
				"Aeropuerto de D�sseldorf", "Aeropuerto de D�sseldorf-Weeze", "Aeropuerto de Erfurt",
				"Aeropuerto de Fr�ncfort", "Aeropuerto de Fr�ncfort-Hahn", "Aeropuerto de Friedrichshafen",
				"Aeropuerto de Hamburgo", "Aeropuerto de Han�ver", "Aeropuerto de Karlsruhe/Baden-Baden",
				"Aeropuerto de Leipzig-Altenburg", "Aeropuerto de Leipzig/Halle", "Aeropuerto de L�beck",
				"Aeropuerto de Mannheim", "Aeropuerto de Memmingen", "Aeropuerto de M�nster/Osnabr�ck",
				"Aeropuerto de M�nich", "Aeropuerto de N�remberg", "Aeropuerto de Paderborn-Lippstadt",
				"Aeropuerto de Rostock-Laage", "Aeropuerto de Saarbr�cken", "Aeropuerto de Stuttgart",
				"Aeropuerto de Sylt" };
		String[] ubicacionAeropuertos = { "Berl�n", "Berl�n", "Berl�n", "Bremen", "Brunswick", "Colonia, Bonn",
				"Dortmund", "Dresde", "D�sseldorf", "Weeze", "Erfurt", "Fr�ncfort del Meno", "Lautzenhausen",
				"Friedrichshafen", "Hamburgo", "Langenhagen", "Rastatt", "Altenburgo", "Leipzig, Halle (Saale)",
				"L�beck", "Mannheim", "Memmingerberg", "M�nster, Osnabr�ck", "Erding", "N�remberg",
				"Paderborn, Lippstadt", "Rostock", "Saarbr�cken", "Stuttgart", "Sylt" };

		if (nombresAeropuertos.length == ubicacionAeropuertos.length) {
			// Creamos los aeropuertos:
			for (int i = 0; i < nombresAeropuertos.length; i++) {
				lista.add(new Aeropuerto(nombresAeropuertos[i], ubicacionAeropuertos[i]));
			}
		}
		return lista;
	}

	public static void main(String[] args) {
		RyanairGateway rg = new RyanairGateway();
		for (Vuelo vuelo : rg.buscarVuelo()) {
			System.out.println(vuelo.toString());
		}
	}
}
