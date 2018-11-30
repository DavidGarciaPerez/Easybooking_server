package assembler;

import java.util.ArrayList;
import java.util.List;

import data.Vuelo;
import data.dto.VueloDTO;

public class VueloAssembler {
	
	private AerolineaAssembler aerolineaAssembler = new AerolineaAssembler();
	private AeropuertoAssembler aeropuertoAssembler = new AeropuertoAssembler();

	public List<VueloDTO> assemble(List<Vuelo> vuelos) {
		List<VueloDTO> vuelosDTO = new ArrayList<>();

		for (Vuelo v : vuelos) {
			vuelosDTO.add(new VueloDTO(v.getNumVuelo(),
					                   v.getHoraSalida(),
					                   v.getHoraLlegada(),
					                   v.getNumAsientos(),
					                   v.getAsientosLibres(),
					                   aeropuertoAssembler.assembleOne(v.getAeropuertoOrigen()),
					                   aeropuertoAssembler.assembleOne(v.getAeropuertoDestino()),
					                   aerolineaAssembler.assembleOne(v.getAerolinea())));
		}

		return vuelosDTO;
	}

	public VueloDTO assembleOne(Vuelo v) {
		return new VueloDTO(v.getNumVuelo(),
                v.getHoraSalida(),
                v.getHoraLlegada(),
                v.getNumAsientos(),
                v.getAsientosLibres(),
                aeropuertoAssembler.assembleOne(v.getAeropuertoOrigen()),
                aeropuertoAssembler.assembleOne(v.getAeropuertoDestino()),
                aerolineaAssembler.assembleOne(v.getAerolinea()));
	}
}
