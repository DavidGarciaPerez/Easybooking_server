package gateways;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import data.Creditcard;

public class VisaGateway implements IPagoGateway {

	// Algunos clientes de las compañias con paypal para realizar los pagos:
	ArrayList<Creditcard> clientes = generarClientes();
	// Los fondos de los clientes:
	HashMap<String, Double> clientesFondos = generarFondos();

	@Override
	public boolean realizarPago(String numTarjetaOrigen, String numTarjetaDestino, double importe, String concepto) {
		// TODO Auto-generated method stub
		boolean dev = false;

		// Primero tenemos que comprobar que exista el sourceCliente y el targetCliente:
		boolean source = false;
		boolean target = false;
		for (Creditcard cliente : clientes) {
			if (cliente.getNumeroTarjeta().equalsIgnoreCase(numTarjetaOrigen)) {
				source = true;
			}
			if (cliente.getNumeroTarjeta().equalsIgnoreCase(numTarjetaDestino)) {
				target = true;
			}
		}

		if (source == true && target == true) {
			double fondosSourceCliente = (double) clientesFondos.get(numTarjetaOrigen);
			double fondosTargetCliente = (double) clientesFondos.get(numTarjetaDestino);

			// Comprobamos antes si el cliente tiene suficientes fondos para pagar el
			// importe querido:
			if (fondosSourceCliente >= importe) {
				// Tiene fondos, entonces restamos el importe a su fondo y lo sumamos al del
				// target:
				clientesFondos.put(numTarjetaOrigen, fondosSourceCliente - importe);
				clientesFondos.put(numTarjetaDestino, fondosTargetCliente + importe);
				System.out.println("EL CLIENTE : " + numTarjetaOrigen + " ACABA DE REALIZAR UNA TRANSACCIÓN DE "
						+ importe + " € EN LA CUENTA DEL CLIENTE " + numTarjetaDestino + "");
				System.out.println("CONCEPTO DE LA TRANSACCIÓN: " + concepto);
				dev = true;
			} else {
				System.err.println("EL CLIENTE : " + numTarjetaOrigen + " NO TIENE FONDOS SUFICIENTES EN SU CUENTA!");
			}

		} else {
			System.err.println("SOURCE CLIENTE ? : " + numTarjetaOrigen + " TARGET CLIENTE ? : " + numTarjetaDestino);
		}

		return dev;
	}

	private ArrayList<Creditcard> generarClientes() {
		ArrayList<Creditcard> dev = new ArrayList<Creditcard>();

		for (int i = 0; i < 100; i++) {
			dev.add(new Creditcard("492156126784999" + String.valueOf(i), 123 + i,
					new Date(System.currentTimeMillis() + 99999)));
		}

		return dev;
	}

	private HashMap<String, Double> generarFondos() {
		HashMap<String, Double> dev = new HashMap<String, Double>();
		for (int i = 0; i < 100; i++) {
			dev.put(clientes.get(i).getNumeroTarjeta(), (double) 5000);
		}
		return dev;
	}

	public static void main(String[] args) {
		VisaGateway vg = new VisaGateway();
		vg.realizarPago("4921561267849990", "4921561267849991", 500, "Progando integro de 500 euros en una VISA");
		vg.realizarPago("4921561267849990", "4921561267849991", 5000, "Progando la falta de fondos en una VISA");
	}

}
