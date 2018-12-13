package services;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ILoginService extends Remote{
	
	public boolean registerUser(String nombre, String email, String sistemaAutentificacion) throws RemoteException;
	public boolean login(String email, String sistemaAutentificacion) throws RemoteException;

}
