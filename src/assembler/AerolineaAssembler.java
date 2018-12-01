package assembler;

import java.util.ArrayList;
import java.util.List;

import data.Aerolinea;
import data.dto.AerolineaDTO;

public class AerolineaAssembler {
	
	public List<AerolineaDTO> assemble(List<Aerolinea> aerolineas) {
		List<AerolineaDTO> aerolineasDTO = new ArrayList<>();

		for (Aerolinea a : aerolineas) {
			aerolineasDTO.add(new AerolineaDTO(a.getNombreAerolinea()));
		}
		
		return aerolineasDTO;
	}
	
	public AerolineaDTO assembleOne(Aerolinea aerolinea) {
		return new AerolineaDTO(aerolinea.getNombreAerolinea());
	}
}
