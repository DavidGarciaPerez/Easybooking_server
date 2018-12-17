package data.dto;

import java.io.Serializable;
import java.util.Date;

public class VueloDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int numVuelo;
	private Date horaSalida;
	private Date horaLlegada;
	private int numAsientos;
	private int asientosLibres;
	private AeropuertoDTO aeropuertoOrigen;
	private AeropuertoDTO aeropuertoDestino;
	private AerolineaDTO aerolinea;

	public VueloDTO(int numVuelo, Date horaSalida, Date horaLlegada, int numAsientos, int asientosLibres,
			AeropuertoDTO aeropuertoOrigen, AeropuertoDTO aeropuertoDestino, AerolineaDTO aerolinea) {
		super();
		this.numVuelo = numVuelo;
		this.horaSalida = horaSalida;
		this.horaLlegada = horaLlegada;
		this.numAsientos = numAsientos;
		this.asientosLibres = asientosLibres;
		this.aeropuertoOrigen = aeropuertoOrigen;
		this.aeropuertoDestino = aeropuertoDestino;
		this.aerolinea = aerolinea;
	}

	public int getNumVuelo() {
		return numVuelo;
	}

	public void setNumVuelo(int numVuelo) {
		this.numVuelo = numVuelo;
	}

	public Date getHoraSalida() {
		return horaSalida;
	}

	public void setHoraSalida(Date horaSalida) {
		this.horaSalida = horaSalida;
	}

	public Date getHoraLlegada() {
		return horaLlegada;
	}

	public void setHoraLlegada(Date horaLlegada) {
		this.horaLlegada = horaLlegada;
	}

	public int getNumAsientos() {
		return numAsientos;
	}

	public void setNumAsientos(int numAsientos) {
		this.numAsientos = numAsientos;
	}

	public int getAsientosLibres() {
		return asientosLibres;
	}

	public void setAsientosLibres(int asientosLibres) {
		this.asientosLibres = asientosLibres;
	}

	public AeropuertoDTO getAeropuertoOrigen() {
		return aeropuertoOrigen;
	}

	public void setAeropuertoOrigen(AeropuertoDTO aeropuertoOrigen) {
		this.aeropuertoOrigen = aeropuertoOrigen;
	}

	public AeropuertoDTO getAeropuertoDestino() {
		return aeropuertoDestino;
	}

	public void setAeropuertoDestino(AeropuertoDTO aeropuertoDestino) {
		this.aeropuertoDestino = aeropuertoDestino;
	}

	public AerolineaDTO getAerolinea() {
		return aerolinea;
	}

	public void setAerolinea(AerolineaDTO aerolinea) {
		this.aerolinea = aerolinea;
	}

}
