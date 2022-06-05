package validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import domain.Tickets;

public class TicketValidation implements Validator {

	@Override
	public boolean supports(Class<?> klass) {
        return Tickets.class.isAssignableFrom(klass);
	}

	@Override
	public void validate(Object target, Errors errors) {
        Tickets registration = (Tickets) target;

        int voetbalcode1 = registration.getVoetbalcode1();
        int voetbalcode2 = registration.getVoetbalcode2();

        if (voetbalcode1 > voetbalcode2) {
            errors.rejectValue("voetbalcode1",
                    "amountOfTickets.registration.voetbalcode1",
                    "voetbalcode 1 moet kleiner zijn dan voetbalcode 2");
        }
        if (voetbalcode2 < voetbalcode1) {
            errors.rejectValue("voetbalcode2",
                    "amountOfTickets.registration.voetbalcode1",
                    "voetbalcode 1 moet kleiner zijn dan voetbalcode 2");
        }
        if (voetbalcode1 == voetbalcode2) {
            errors.rejectValue("voetbalcode1",
                    "amountOfTickets.registration.voetbalcode1",
                    "voetbalcode 1 en voetbalcode 2 zijn gelijk");
            errors.rejectValue("voetbalcode2",
                    "amountOfTickets.registration.voetbalcode1",
                    "voetbalcode 1 en voetbalcode 2 zijn gelijk");
        }
	}

}
