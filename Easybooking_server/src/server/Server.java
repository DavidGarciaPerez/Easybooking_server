package server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import clases.Pago;
import clases.Reserva;
import clases.Vuelo;

public class Server extends UnicastRemoteObject implements IServer {

	private static final long serialVersionUID = 1L;// Serializable

	// Constructor por defecto:
	protected Server() throws RemoteException {
		super();
	}

	// Método main para invocar servidor:
	public static void main(String[] args) throws RemoteException, MalformedURLException{
		if (args.length != 3) {
			System.out.println("usage: java [policy] [codebase] server.Server [host] [port] [server]");
			System.exit(0);
		}

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		String name = "//" + args[0] + ":" + args[1] + "/" + args[2];

		try {
			IServer objServer = new Server();
			Naming.rebind(name, objServer);
			System.out.println("* Server '" + name + "' active and waiting...");
		} catch (Exception e) {
			System.err.println("- Exception running the server: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public boolean login(String email, String sistemaAutentificacion) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean registerUser(String nombre, String email, String sistemaAutentificacion) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Vuelo> buscarVuelos(Vuelo vueloABuscar) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean realizarReserva(Reserva reservaARealizar, Pago pagoARealizar) throws RemoteException{
		// TODO Auto-generated method stub
		return false;
	}

}