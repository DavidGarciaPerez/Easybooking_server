package services;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import data.dto.ReservaDTO;
import data.dto.VueloDTO;

public interface IVueloService extends Remote{

	public List<VueloDTO> buscarVuelos(String origen, String destino, int nPlazas) throws RemoteException;
	//public boolean realizarReserva(ReservaDTO reservaARealizar, int nPlazas, String[] pasajeros) throws RemoteException;
	public boolean realizarReserva(VueloDTO vuelo, int nPlazas, String[] pasajeros) throws RemoteException;
}
