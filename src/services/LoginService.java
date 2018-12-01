package services;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import server.Server;

//Esta clase implementa la Fachada y AppService Patterns:
public class LoginService extends UnicastRemoteObject implements ILoginService{

	private static final long serialVersionUID = 1L;
	private Server server;

	public LoginService(Server server) throws RemoteException {
		super();
		this.server = server;
	}

	@Override
	public boolean registerUser(String nombre, String email, String sistemaAutentificacion) throws RemoteException {
		// TODO Auto-generated method stub
		this.server.registerUser(nombre, email, sistemaAutentificacion);
		return false;
	}

	@Override
	public boolean login(String email, String sistemaAutentificacion) throws RemoteException {
		// TODO Auto-generated method stub
		this.server.login(email, sistemaAutentificacion);
		return false;
	}
	
	
}
