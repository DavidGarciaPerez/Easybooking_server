package gateways;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import data.Aerolinea;
import data.Aeropuerto;
import data.Vuelo;

public class VuelingGateway implements IAerolineaGateway {

	private String[] nombresAeropuertos = { "Aeropuerto de Berl�n-Sch�nefeld", "Aeropuerto de Berl�n-Tegel",
			"Aeropuerto de Berl�n-Tempelhof", "Aeropuerto de Bremen", "Aeropuerto de Brunswick/Wolfsburg",
			"Aeropuerto de Colonia/Bonn", "Aeropuerto de Dortmund", "Aeropuerto de Dresde", "Aeropuerto de D�sseldorf",
			"Aeropuerto de D�sseldorf-Weeze", "Aeropuerto de Erfurt", "Aeropuerto de Fr�ncfort",
			"Aeropuerto de Fr�ncfort-Hahn", "Aeropuerto de Friedrichshafen", "Aeropuerto de Hamburgo",
			"Aeropuerto de Han�ver", "Aeropuerto de Karlsruhe/Baden-Baden", "Aeropuerto de Leipzig-Altenburg",
			"Aeropuerto de Leipzig/Halle", "Aeropuerto de L�beck", "Aeropuerto de Mannheim", "Aeropuerto de Memmingen",
			"Aeropuerto de M�nster/Osnabr�ck", "Aeropuerto de M�nich", "Aeropuerto de N�remberg",
			"Aeropuerto de Paderborn-Lippstadt", "Aeropuerto de Rostock-Laage", "Aeropuerto de Saarbr�cken",
			"Aeropuerto de Stuttgart", "Aeropuerto de Sylt" };
	private String[] ubicacionAeropuertos = { "Berl�n", "Berl�n", "Berl�n", "Bremen", "Brunswick", "Colonia, Bonn",
			"Dortmund", "Dresde", "D�sseldorf", "Weeze", "Erfurt", "Fr�ncfort del Meno", "Lautzenhausen",
			"Friedrichshafen", "Hamburgo", "Langenhagen", "Rastatt", "Altenburgo", "Leipzig, Halle (Saale)", "L�beck",
			"Mannheim", "Memmingerberg", "M�nster, Osnabr�ck", "Erding", "N�remberg", "Paderborn, Lippstadt", "Rostock",
			"Saarbr�cken", "Stuttgart", "Sylt" };

	private ArrayList<Aeropuerto> aeropuertos = generarAeropuertos();
	private ArrayList<Vuelo> VUELOS_VUELING;

	public VuelingGateway() {
		super();
		// TODO Auto-generated constructor stub
		this.VUELOS_VUELING = this.generarListVuelos();
	}

	public ArrayList<Vuelo> getVUELOS_VUELING() {
		return this.VUELOS_VUELING;
	}

	public void setVUELOS_VUELING(ArrayList<Vuelo> vUELOS_VUELING) {
		this.VUELOS_VUELING = vUELOS_VUELING;
	}

	public void decrementarPlazasLibres(Vuelo vuelo) {
		// Buscamos el vuelo y decrementamos sus plazas:
		for (int i = 0; i < this.VUELOS_VUELING.size(); i++) {
			if (this.VUELOS_VUELING.get(i).getNumVuelo() == vuelo.getNumVuelo()) {
				// Decrementamos plazas libres de este vuelo:
				this.VUELOS_VUELING.get(i)
						.setAsientosLibres(this.VUELOS_VUELING.get(i).getAsientosLibres() - vuelo.getAsientosLibres());
			}
		}
	}

	@Override
	public ArrayList<Vuelo> buscarVuelo() {
		// TODO Auto-generated method stub
		return this.VUELOS_VUELING;
	}

	// M�todo para generrar una lista de vuelos, de esta forma estamos simulando que
	// el servicio de Vueling nos proporcione sus vuelos...
	private ArrayList<Vuelo> generarListVuelos() {
		ArrayList<Vuelo> vuelos = new ArrayList<Vuelo>();
		int contadorNumVuelo = 0;
		// Crearemos una lista con todas las iteraciones posibles entre ubicaciones:
		for (int i = 0; i < aeropuertos.size(); i++) {
			for (int j = 0; j < aeropuertos.size(); j++) {
				if (i != j) {
					// Para cada destino generamos 5 alternativas de horarios!:
					for (int k = 0; k < 5; k++) {
						int numVuelo = 1000 + contadorNumVuelo;
						Date horaSalida = generarRandomDate(new Date(System.currentTimeMillis()), new Date(
								System.currentTimeMillis() + new Random().nextInt(9999999 - 999999 + 1) + 999999));
						Date horaLlegada = generarRandomDate(new Date(System.currentTimeMillis()), new Date(
								System.currentTimeMillis() + new Random().nextInt(99999999 - 9999999 + 1) + 9999999));
						int numAsientos = new Random().nextInt(350 - 250 + 1) + 250; // 350 = maximo, 250 = min
						int asientosLibres = new Random().nextInt(numAsientos - 0 + 1) + 0;

						Aeropuerto aeropuertoOrigen = this.aeropuertos.get(i);
						Aeropuerto aeropuertoDestino = this.aeropuertos.get(j);

						Aerolinea aerolinea = new Aerolinea("VUELING");

						// Una vez creados los par�metros necesarios de modo casi aleatorio ya podemos
						// guardarlos en la lista:
						vuelos.add(new Vuelo(numVuelo, horaSalida, horaLlegada, numAsientos, asientosLibres,
								aeropuertoOrigen, aeropuertoDestino, aerolinea));

						contadorNumVuelo++;
					}
				}
			}

		}

		return vuelos;
	}

	private Date generarRandomDate(Date d1, Date d2) {
		return new Date(ThreadLocalRandom.current().nextLong(d1.getTime(), d2.getTime()));
	}

	private ArrayList<Aeropuerto> generarAeropuertos() {
		ArrayList<Aeropuerto> lista = new ArrayList<Aeropuerto>();
		if (nombresAeropuertos.length == ubicacionAeropuertos.length) {
			// Creamos los aeropuertos:
			for (int i = 0; i < nombresAeropuertos.length; i++) {
				lista.add(new Aeropuerto(nombresAeropuertos[i], ubicacionAeropuertos[i]));
			}
		}
		return lista;
	}

	public static void main(String[] args) {
		VuelingGateway vg = new VuelingGateway();
		for (Vuelo vuelo : vg.buscarVuelo()) {
			System.out.println(vuelo.toString());
		}
	}

}
