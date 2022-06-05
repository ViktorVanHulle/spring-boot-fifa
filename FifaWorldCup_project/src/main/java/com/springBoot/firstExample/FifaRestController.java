package com.springBoot.firstExample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.VoetbalDao;


//POSTMAN
@RestController
@RequestMapping(value = "/fifaDetail")
public class FifaRestController {
	
	@Autowired
	private VoetbalDao voetbalService;
	
    @GetMapping(value = "/{id}") 
    public String[] getWedstrijdTicketDetails(@PathVariable("id") int id) {
    	return voetbalService.getWedstrijd(id).getWedstrijd().getLanden().split(",");
    }

	public void setVoetbalService(VoetbalDao voetbalService) {
		this.voetbalService = voetbalService;
	}
    
    

}
