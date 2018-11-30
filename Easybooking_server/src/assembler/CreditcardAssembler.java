package assembler;

import data.Creditcard;
import data.dto.CreditcardDTO;

public class CreditcardAssembler {
	
	public CreditcardDTO assembleOne(Creditcard creditcard) {
		return new CreditcardDTO(creditcard.getNumeroTarjeta(), creditcard.getCvv2(), creditcard.getFechaCaducidad());
	}

}
