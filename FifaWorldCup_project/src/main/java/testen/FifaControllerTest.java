package testen;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.security.Principal;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.springBoot.firstExample.FifaController;

import domain.Stadium;
import domain.Tickets;
import domain.Wedstrijd;
import domain.WedstrijdTicket;
import service.VoetbalDao;
import validator.TicketValidation;


public class FifaControllerTest {
	
	VoetbalDao voetbalService;
	
	@BeforeEach
	void beforeEach() {
		voetbalService = Mockito.mock(VoetbalDao.class);
    	when(voetbalService.getWedstrijd(10)).thenReturn(new WedstrijdTicket(new Wedstrijd(1, "België,Canada", new Date(), 21), 35));
	}

	@Test
	void loginSuccesfullyTest() {

		Model m = Mockito.mock(Model.class);

		int id = 10;
		Stadium stadium = new Stadium();
		stadium.setValue("Lusail Stadium");

		FifaController fifaController = new FifaController(voetbalService, id, stadium);
		assertEquals("login", fifaController.login(null, "user", m));
	}

	@Test
	void badLoginTest() {

		Model m = Mockito.mock(Model.class);
		VoetbalDao voetbalService = Mockito.mock(VoetbalDao.class);

		int id = 10;
		Stadium stadium = new Stadium();
		stadium.setValue("Lusail Stadium");

		FifaController fifaController = new FifaController(voetbalService, id, stadium);
		assertEquals("login", fifaController.login("there is an error", "user", m));
	}

	@Test
	void showHomePageTest() {

		Model m = Mockito.mock(Model.class);
		Principal principal = Mockito.mock(Principal.class);
		VoetbalDao voetbalService = Mockito.mock(VoetbalDao.class);

		int id = 0;
		Stadium stadium = new Stadium();

		when(principal.getName()).thenReturn("admin");

		FifaController fifaController = new FifaController(voetbalService, id, stadium);
		assertEquals("stadiumForm", fifaController.showHomePage(m, principal));
	}

	@Test
	void redirectToFifaPageTest() {

		Model m = Mockito.mock(Model.class);

		int id = 10;
		Stadium stadium = new Stadium();
		stadium.setValue("Lusail Stadium");

		FifaController fifaController = new FifaController(voetbalService, id, stadium);
		assertEquals("fifa", fifaController.redirect(m));

	}

	@Test
	void wedstrijdTest() {

		Model m = Mockito.mock(Model.class);
		int idTest = 10;

		int id = 10;
		Stadium stadium = new Stadium();
		stadium.setValue("Lusail Stadium");

		FifaController fifaController = new FifaController(voetbalService, id, stadium);
		assertEquals("ticketForm", fifaController.wedstrijd(idTest, m));
	}

	@Test
	void soldOut() {
 
		Model m = Mockito.mock(Model.class);

		int id = 1;
		Stadium stadium = new Stadium();
		stadium.setValue("Al Bayt Stadium");
		
		voetbalService = Mockito.mock(VoetbalDao.class);
    	when(voetbalService.getWedstrijd(1)).thenReturn(new WedstrijdTicket(new Wedstrijd(id, "België,Canada", new Date(), 21), 0));

		FifaController fifaController = new FifaController(voetbalService, id,stadium);
		assertEquals("soldOut", fifaController.wedstrijd(id, m));

	}

	@Test
	void returnToHomeNoErrorsTest() {

		Model m = Mockito.mock(Model.class);
		BindingResult result = Mockito.mock(BindingResult.class);
		TicketValidation tv = mock(TicketValidation.class);
		Tickets ticketsTest = new Tickets();
		ticketsTest.setEmail("thisisatest@gmail.com");
		ticketsTest.setVoetbalcode1(10);
		ticketsTest.setVoetbalcode2(20);
		ticketsTest.setValue(1);
		
		doNothing().when(tv).validate(ticketsTest, result);
		when(result.hasErrors()).thenReturn(false);
		
		int id = 10;
		Stadium stadium = new Stadium();
		stadium.setValue("Lusail Stadium");

		FifaController fifaController = new FifaController(voetbalService, id, stadium, tv);
		assertEquals("stadiumForm", fifaController.returnToHome(ticketsTest, result, m));
	}

	@Test
	void returnToHomeErrorsTest() {

		Model m = Mockito.mock(Model.class);
		BindingResult result = Mockito.mock(BindingResult.class);
		TicketValidation tv = mock(TicketValidation.class);
		Tickets ticketsTest = new Tickets();
		ticketsTest.setEmail("thisisatest@gmail.com");
		ticketsTest.setVoetbalcode1(10);
		ticketsTest.setVoetbalcode2(20);
		ticketsTest.setValue(1);
		
		doNothing().when(tv).validate(ticketsTest, result);
		when(result.hasErrors()).thenReturn(true);
		
		int id = 10;
		Stadium stadium = new Stadium();
		stadium.setValue("Lusail Stadium");

		FifaController fifaController = new FifaController(voetbalService, id, stadium, tv);
		assertEquals("ticketForm", fifaController.returnToHome(ticketsTest, result, m));
	}

	@Test
	void ticketsBesteldTest() {
		
		Tickets ticketsTest = new Tickets();
		ticketsTest.setValue(1);
		
		int id = 10;

		int ticketResult = voetbalService.getWedstrijd(id).getTickets() - 1;
		
		voetbalService.getWedstrijd(id).ticketsKopen(ticketsTest.getValue());

		assertEquals(ticketResult, voetbalService.getWedstrijd(id).getTickets());
	}

}
