package assembler;

import java.util.ArrayList;
import java.util.List;

import data.Reserva;
import data.dto.ReservaDTO;

public class ReservaAssembler {

	private UsuarioAssembler usuarioAssembler = new UsuarioAssembler();
	private VueloAssembler vueloAssembler = new VueloAssembler();
	private PagoAssembler pagoAssembler = new PagoAssembler();

	public List<ReservaDTO> assemble(List<Reserva> reservas) {
		List<ReservaDTO> reservasDTO = new ArrayList<>();

		for (Reserva r : reservas) {
			reservasDTO.add(new ReservaDTO(r.getPrecio(),
										   r.getAsiento(),
										   usuarioAssembler.assembleOne(r.getUsuario()),
										   vueloAssembler.assembleOne(r.getVuelo()),
										   pagoAssembler.assembleOne(r.getPago())));
		}

		return reservasDTO;
	}
}
