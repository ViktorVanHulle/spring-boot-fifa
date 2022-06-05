package testen;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.springBoot.firstExample.HomeController;

public class HomeControllerTest {
	
	@Test
	void directToRightPathTest() {
				
		HomeController homeController = new HomeController();
		assertEquals("redirect:/fifaWorldCup", homeController.showHomePage());
	}
	
}
