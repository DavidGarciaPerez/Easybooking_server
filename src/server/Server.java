package server;

import java.rmi.RemoteException;
import java.util.ArrayList;

import data.Reserva;
import data.Vuelo;

public class Server{

//	private static final long serialVersionUID = 1L;// Serializable
//	
//
//	// Constructor por defecto:
//	protected Server() throws RemoteException {
//		super();
//	}
//
//	// Método main para invocar servidor:
//	public static void main(String[] args) throws RemoteException, MalformedURLException{
//		if (args.length != 3) {
//			System.out.println("usage: java [policy] [codebase] server.Server [host] [port] [server]");
//			System.exit(0);
//		}
//
//		if (System.getSecurityManager() == null) {
//			System.setSecurityManager(new SecurityManager());
//		}
//
//		String name = "//" + args[0] + ":" + args[1] + "/" + args[2];
//
//		try {
//			IServer objServer = new Server();
//			Naming.rebind(name, objServer);
//			System.out.println("* Server '" + name + "' active and waiting...");
//		} catch (Exception e) {
//			System.err.println("- Exception running the server: " + e.getMessage());
//			e.printStackTrace();
//		}
//	}
	
	public synchronized boolean login(String email, String sistemaAutentificacion) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	public synchronized boolean registerUser(String nombre, String email, String sistemaAutentificacion) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	public synchronized ArrayList<Vuelo> buscarVuelos(String origen, String destino, int nPlazas) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public synchronized boolean realizarReserva(Reserva reservaARealizar, int nPlazas, String[] pasajeros)
			throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

}