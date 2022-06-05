package testen;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;

import org.junit.Test;
import org.mockito.Mockito;
import com.springBoot.firstExample.FifaRestController;

import domain.Wedstrijd;
import domain.WedstrijdTicket;
import service.VoetbalDao;

public class RestControllerTest {
	

    @Test
    public void getWedstrijdTicketDetailsTest() 
    {
    	
    	VoetbalDao voetbalService = Mockito.mock(VoetbalDao.class);
    	
    	when(voetbalService.getWedstrijd(1)).thenReturn(new WedstrijdTicket(new Wedstrijd(1, "België,Canada", new Date(), 21), 35));
    	FifaRestController frc = new FifaRestController();
    	
    	frc.setVoetbalService(voetbalService);
    	
    	assertArrayEquals(voetbalService.getWedstrijd(1).getWedstrijd().getLanden().split(","), frc.getWedstrijdTicketDetails(1));
    }

}
