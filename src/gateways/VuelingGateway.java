package gateways;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import data.Aerolinea;
import data.Aeropuerto;
import data.Vuelo;

public class VuelingGateway implements IAerolineaGateway {

	private String[] nombresAeropuertos = { "Aeropuerto de Berlín-Schönefeld", "Aeropuerto de Berlín-Tegel",
			"Aeropuerto de Berlín-Tempelhof", "Aeropuerto de Bremen", "Aeropuerto de Brunswick/Wolfsburg",
			"Aeropuerto de Colonia/Bonn", "Aeropuerto de Dortmund", "Aeropuerto de Dresde", "Aeropuerto de Düsseldorf",
			"Aeropuerto de Düsseldorf-Weeze", "Aeropuerto de Erfurt", "Aeropuerto de Fráncfort",
			"Aeropuerto de Fráncfort-Hahn", "Aeropuerto de Friedrichshafen", "Aeropuerto de Hamburgo",
			"Aeropuerto de Hanóver", "Aeropuerto de Karlsruhe/Baden-Baden", "Aeropuerto de Leipzig-Altenburg",
			"Aeropuerto de Leipzig/Halle", "Aeropuerto de Lübeck", "Aeropuerto de Mannheim", "Aeropuerto de Memmingen",
			"Aeropuerto de Münster/Osnabrück", "Aeropuerto de Múnich", "Aeropuerto de Núremberg",
			"Aeropuerto de Paderborn-Lippstadt", "Aeropuerto de Rostock-Laage", "Aeropuerto de Saarbrücken",
			"Aeropuerto de Stuttgart", "Aeropuerto de Sylt" };
	private String[] ubicacionAeropuertos = { "Berlín", "Berlín", "Berlín", "Bremen", "Brunswick", "Colonia, Bonn",
			"Dortmund", "Dresde", "Düsseldorf", "Weeze", "Erfurt", "Fráncfort del Meno", "Lautzenhausen",
			"Friedrichshafen", "Hamburgo", "Langenhagen", "Rastatt", "Altenburgo", "Leipzig, Halle (Saale)", "Lübeck",
			"Mannheim", "Memmingerberg", "Münster, Osnabrück", "Erding", "Núremberg", "Paderborn, Lippstadt", "Rostock",
			"Saarbrücken", "Stuttgart", "Sylt" };

	private ArrayList<Aeropuerto> aeropuertos = generarAeropuertos();
	private ArrayList<Vuelo> VUELOS_VUELING;

	public VuelingGateway() {
		super();
		// TODO Auto-generated constructor stub
		this.VUELOS_VUELING = this.generarListVuelos();
	}

	@Override
	public ArrayList<Vuelo> buscarVuelo() {
		// TODO Auto-generated method stub
		return this.VUELOS_VUELING;
	}

	// Método para generrar una lista de vuelos, de esta forma estamos simulando que
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

						// Una vez creados los parámetros necesarios de modo casi aleatorio ya podemos
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
