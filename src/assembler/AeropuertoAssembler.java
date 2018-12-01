package assembler;

import java.util.ArrayList;
import java.util.List;

import data.Aeropuerto;
import data.dto.AeropuertoDTO;

public class AeropuertoAssembler {
	
	public List<AeropuertoDTO> assemble(List<Aeropuerto> aeropuertos) {
		List<AeropuertoDTO> aeropuertosDTO = new ArrayList<>();

		for (Aeropuerto a : aeropuertos) {
			aeropuertosDTO.add(new AeropuertoDTO(a.getNombre(), a.getUbicacion()));
		}
		
		return aeropuertosDTO;
	}
	
	public AeropuertoDTO assembleOne(Aeropuerto aeropuerto) {
		return new AeropuertoDTO(aeropuerto.getNombre(), aeropuerto.getUbicacion());
	}
}
