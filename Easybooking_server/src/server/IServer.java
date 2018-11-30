package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import data.Pago;
import data.Reserva;
import data.Vuelo;

public interface IServer extends Remote {
	boolean registerUser(String nombre, String email, String sistemaAutentificacion) throws RemoteException;
	boolean login(String email, String sistemaAutentificacion) throws RemoteException;
	ArrayList<Vuelo> buscarVuelos(Vuelo vueloABuscar) throws RemoteException;
	boolean realizarReserva(Reserva reservaARealizar, Pago pagoARealizar) throws RemoteException;
}