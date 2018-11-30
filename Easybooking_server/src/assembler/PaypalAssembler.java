package assembler;

import data.Paypal;
import data.dto.PaypalDTO;

public class PaypalAssembler {
	
	public PaypalDTO assembleOne(Paypal paypal) {
		return new PaypalDTO(paypal.getNombreUsuario());
	}

}
