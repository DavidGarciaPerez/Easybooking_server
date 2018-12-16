package gateways;

import java.util.ArrayList;
import java.util.HashMap;

import data.Paypal;

public class PaypalGateway implements IPagoGateway {

	// Algunos clientes de las compañias con paypal para realizar los pagos:
	ArrayList<Paypal> clientes = generarClientes();
	// Los fondos de los clientes:
	HashMap<String, Double> clientesFondos = generarFondos();

	@Override
	public boolean realizarPago(String sourceCliente, String targetCliente, double importe, String concepto) {
		// TODO Auto-generated method stub
		boolean dev = false;

		// Primero tenemos que comprobar que exista el sourceCliente y el targetCliente:
		boolean source = false;
		boolean target = false;
		for (Paypal cliente : clientes) {
			if (cliente.getNombreUsuario().equalsIgnoreCase(sourceCliente)) {
				source = true;
			}
			if (cliente.getNombreUsuario().equalsIgnoreCase(targetCliente)) {
				target = true;
			}
		}

		if (source == true && target == true) {
			double fondosSourceCliente = (double) clientesFondos.get(sourceCliente);
			double fondosTargetCliente = (double) clientesFondos.get(targetCliente);

			// Comprobamos antes si el cliente tiene suficientes fondos para pagar el
			// importe querido:
			if (fondosSourceCliente >= importe) {
				// Tiene fondos, entonces restamos el importe a su fondo y lo sumamos al del
				// target:
				clientesFondos.put(sourceCliente, fondosSourceCliente - importe);
				clientesFondos.put(targetCliente, fondosTargetCliente + importe);
				System.out.println("EL CLIENTE : " + sourceCliente + " ACABA DE REALIZAR UNA TRANSACCIÓN DE " + importe
						+ " € EN LA CUENTA DEL CLIENTE " + targetCliente + "");
				System.out.println("CONCEPTO DE LA TRANSACCIÓN: " + concepto);
				dev = true;
			} else {
				System.err.println("EL CLIENTE : " + sourceCliente + " NO TIENE FONDOS SUFICIENTES EN SU CUENTA!");
			}

		} else {
			System.err.println("SOURCE CLIENTE ? : " + sourceCliente + " TARGET CLIENTE ? : " + targetCliente);
		}

		return dev;
	}

	private ArrayList<Paypal> generarClientes() {
		ArrayList<Paypal> dev = new ArrayList<Paypal>();

		for (int i = 0; i < 100; i++) {
			dev.add(new Paypal("PAYPAL_ACCOUNT_" + i));
		}

		return dev;
	}

	private HashMap<String, Double> generarFondos() {
		HashMap<String, Double> dev = new HashMap<String, Double>();
		for (int i = 0; i < 100; i++) {
			dev.put(clientes.get(i).getNombreUsuario(), (double) 5000);
		}
		return dev;
	}

	public static void main(String[] args) {
		PaypalGateway pg = new PaypalGateway();
		pg.realizarPago("PAYPAL_ACCOUNT_1", "PAYPAL_ACCOUNT_2", 500,"Progando integro de 500 euros en una cuenta de paypal");
		pg.realizarPago("PAYPAL_ACCOUNT_1", "PAYPAL_ACCOUNT_2", 5000,"Progando la falta de fondos en una cuenta de paypal");
	}

}
