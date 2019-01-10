package services;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import assembler.ReservaAssembler;
import assembler.VueloAssembler;
import data.Aerolinea;
import data.Aeropuerto;
import data.Creditcard;
import data.Pago;
import data.Paypal;
import data.Reserva;
import data.Usuario;
import data.Vuelo;
import data.dto.ReservaDTO;
import data.dto.UsuarioDTO;
import data.dto.VueloDTO;
import server.Server;

//Esta clase implementa la Fachada y AppService Patterns:
public class VueloService extends UnicastRemoteObject implements IVueloService {

	private static final long serialVersionUID = 1L;
	private Server server;

	public VueloService(Server server) throws RemoteException {
		super();
		this.server = server;
	}

	@Override
	public List<VueloDTO> buscarVuelos(String origen, String destino, int nPlazas) throws RemoteException {
		// TODO Auto-generated method stub
		// Transformación de DATA a DTO:
		VueloAssembler assembler = new VueloAssembler();
		return assembler.assemble(server.buscarVuelos(origen, destino, nPlazas));
	}

	@Override
	public boolean realizarReserva(ReservaDTO reservaARealizar, int nPlazas, String[] pasajeros)
			throws RemoteException {
		// TODO Auto-generated method stub
		// Transformación de DTO a DATA:
		Usuario usuario = new Usuario(reservaARealizar.getUsuario().getNombre(),
				reservaARealizar.getUsuario().getEmail(), reservaARealizar.getUsuario().getSistemaAutentificacion());
		Aeropuerto aeropuertoOrigen = new Aeropuerto(reservaARealizar.getVuelo().getAeropuertoOrigen().getNombre(),
				reservaARealizar.getVuelo().getAeropuertoOrigen().getUbicacion());
		Aeropuerto aeropuestoDestino = new Aeropuerto(reservaARealizar.getVuelo().getAeropuertoDestino().getNombre(),
				reservaARealizar.getVuelo().getAeropuertoDestino().getUbicacion());
		Aerolinea aerolinea = new Aerolinea(reservaARealizar.getVuelo().getAerolinea().getNombreAerolinea());
		Vuelo vuelo = new Vuelo(reservaARealizar.getVuelo().getNumVuelo(), reservaARealizar.getVuelo().getHoraSalida(),
				reservaARealizar.getVuelo().getHoraLlegada(), reservaARealizar.getVuelo().getNumAsientos(),
				reservaARealizar.getVuelo().getAsientosLibres(), aeropuertoOrigen, aeropuestoDestino, aerolinea);
		Paypal paypal = new Paypal(reservaARealizar.getPago().getPaypal().getNombreUsuario());
		Creditcard creditcard = new Creditcard(reservaARealizar.getPago().getCreditcard().getNumeroTarjeta(),
				reservaARealizar.getPago().getCreditcard().getCvv2(),
				reservaARealizar.getPago().getCreditcard().getFechaCaducidad());
		Pago pago = new Pago(paypal, creditcard, reservaARealizar.getPago().getImporte(),
				reservaARealizar.getPago().getConcepto());
		Reserva reserva = new Reserva(reservaARealizar.getPrecio(), reservaARealizar.getAsiento(), usuario, vuelo,
				pago);

		// Una vez transformado podemos pasar ya los datos:
		return this.server.realizarReserva(reserva, nPlazas, pasajeros);
	}

	@Override
	public List<ReservaDTO> getReservas(UsuarioDTO usuario) throws RemoteException {
		// TODO Auto-generated method stub
		// Transformación de DATA a DTO:
		ReservaAssembler assembler = new ReservaAssembler();
		return assembler.assemble(server.getReservas(
				new Usuario(usuario.getNombre(), usuario.getEmail(), usuario.getSistemaAutentificacion())));
	}
}
