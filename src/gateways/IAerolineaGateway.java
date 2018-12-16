package gateways;

import java.util.ArrayList;

import data.Vuelo;

public interface IAerolineaGateway {
	public ArrayList<Vuelo> buscarVuelo();
}
