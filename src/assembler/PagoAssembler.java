package assembler;

import java.util.ArrayList;
import java.util.List;

import data.Pago;
import data.dto.PagoDTO;

public class PagoAssembler {

	private PaypalAssembler paypalAssemblerOne = new PaypalAssembler();
	private CreditcardAssembler creditcardAssemblerOne = new CreditcardAssembler();

	public List<PagoDTO> assemble(List<Pago> pagos) {
		List<PagoDTO> pagosDTO = new ArrayList<>();

		for (Pago p : pagos) {
			pagosDTO.add(new PagoDTO(paypalAssemblerOne.assembleOne(p.getPaypal()),
					creditcardAssemblerOne.assembleOne(p.getCreditcard()), p.getImporte(), p.getConcepto()));
		}

		return pagosDTO;
	}

	public PagoDTO assembleOne(Pago p) {
		return new PagoDTO(paypalAssemblerOne.assembleOne(p.getPaypal()),
				creditcardAssemblerOne.assembleOne(p.getCreditcard()), p.getImporte(), p.getConcepto());
	}
}
