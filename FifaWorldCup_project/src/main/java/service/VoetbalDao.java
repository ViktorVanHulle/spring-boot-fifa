package service;

import java.util.List;

import domain.WedstrijdTicket;

public interface VoetbalDao {
	
	public List<WedstrijdTicket> getWedstrijdenByStadium(String stadium);

	public WedstrijdTicket getWedstrijd(int id);
	
	public WedstrijdTicket update(WedstrijdTicket object);
	
	public String[] getStadiumList();
	
	
			
}
