package services;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ILoginService extends Remote{
	
	boolean registerUser(String nombre, String email, String sistemaAutentificacion) throws RemoteException;
	boolean login(String email, String sistemaAutentificacion) throws RemoteException;

}
