package com.springBoot.firstExample;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import domain.Stadium;
import domain.Tickets;
import service.VoetbalDao;
import validator.TicketValidation;


@Controller
public class FifaController {
	
	private Stadium stadium;
	private String paragraphTicket;
	private int id;
	private String name = "";
	
	@Autowired
	private VoetbalDao voetbalService;
	
    @Autowired
    private TicketValidation ticketValidation;
    
    public FifaController() {

	}
    
    //JUNIT TESTEN CONSTRUCTORS
    public FifaController(VoetbalDao voetbalService2, int id, Stadium stadium) {
    	this.id = id;
    	this.stadium = stadium;
    	this.voetbalService = voetbalService2;
	}
    public FifaController(VoetbalDao voetbalService2, int id, Stadium stadium, TicketValidation tv) {
    	this.id = id;
    	this.stadium = stadium;
    	this.voetbalService = voetbalService2;
    	this.ticketValidation = tv;
	}

	@RequestMapping(value = "/login")
    public String login(@RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout, Model model) {
    	
    	
        if (error != null) {
            model.addAttribute("error", "Invalid username and password!");
        }
        if (logout != null) {
            model.addAttribute("msg", "You've been logged out successfully.");
        }
        return "login";
    }

	@GetMapping("/fifaWorldCup")
	public String showHomePage(Model model, Principal principal) {
		
		if(principal != null) {
	    	this.name = principal.getName();		
		}
		
		model.addAttribute("stadiums", voetbalService.getStadiumList());
		model.addAttribute("stadium", new Stadium());
		model.addAttribute("loginState", !name.isEmpty());
				
		return "stadiumForm";
	}

	@RequestMapping(value="/fifa", method=RequestMethod.POST)
	public String onSubmit(@ModelAttribute Stadium stadium, Model model) {
		
		this.stadium = stadium;
				
		model.addAttribute("loginState", !name.isEmpty());
		
		model.addAttribute("stadium", this.stadium.getValue());
		model.addAttribute("lijstWedstrijden", voetbalService.getWedstrijdenByStadium(this.stadium.getValue()));
		model.addAttribute("lijstWedstrijden", voetbalService.getWedstrijdenByStadium(this.stadium.getValue()));
	
		
		return "fifa";
	}
	
	@RequestMapping(value="/fifa", method=RequestMethod.GET)
	public String redirect(Model model) {
		
		model.addAttribute("loginState", !name.isEmpty());
		
		model.addAttribute("stadium", this.stadium.getValue());
		model.addAttribute("lijstWedstrijden", voetbalService.getWedstrijdenByStadium(this.stadium.getValue()));
		model.addAttribute("lijstWedstrijden", voetbalService.getWedstrijdenByStadium(this.stadium.getValue()));

		
		return "fifa";
	}
	
	@RequestMapping("/fifa/{id}")
	public String wedstrijd(@PathVariable int id, Model model) {
		this.id = id;
		if(voetbalService.getWedstrijd(id).uitverkocht()) return "soldOut";
		model.addAttribute("stadium", stadium.getValue());
		model.addAttribute("tickets", new Tickets());
		model.addAttribute("wedstrijd", voetbalService.getWedstrijd(id));
				
		return "ticketForm";	
	}
	
	@RequestMapping(value="/fifaWorldCup", method=RequestMethod.POST)
	public final String returnToHome(@ModelAttribute("tickets")@Valid Tickets tickets, BindingResult result, Model model) {
		
		model.addAttribute("loginState", !name.isEmpty());

		//validating fields
        ticketValidation.validate(tickets, result);
        
		// error occured  
        if(result.hasErrors()) {
        	
        	model.addAttribute("org.springframework.validation.BeanPropertyBindingResult", result);
        	model.addAttribute("tickets", tickets);
			model.addAttribute("error", true);
	        model.addAttribute("stadium", stadium.getValue());
			model.addAttribute("wedstrijd", voetbalService.getWedstrijd(id));


        	return "ticketForm";
        }
        
        //ticket(s) bought
		voetbalService.getWedstrijd(id).ticketsKopen(tickets.getValue());
		voetbalService.update(voetbalService.getWedstrijd(id));
        
        //return to homepage
		this.paragraphTicket = String.format("%d %s aangekocht", tickets.getValue(), tickets.getValue() == 1 ? "ticket werd" : "tickets werden");
		model.addAttribute("tickets", paragraphTicket);
		model.addAttribute("stadium", new Stadium());
		model.addAttribute("stadiums", voetbalService.getStadiumList());
		
		return "stadiumForm";
	}

}

