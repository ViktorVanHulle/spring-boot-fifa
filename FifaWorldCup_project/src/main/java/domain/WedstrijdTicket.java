package domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

//Aantal tickets beschikbaar per wedstrijd
@Entity
@NamedQueries({
	@NamedQuery(name="WedstrijdTicket.getWedstrijdenByStadium", query="SELECT w FROM WedstrijdTicket w WHERE w.stadium = :stadium"),
	@NamedQuery(name="WedstrijdTicket.getWedstrijd", query="SELECT w from WedstrijdTicket w WHERE w.wedstrijd_id = :id"),
})
public class WedstrijdTicket implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int wedstrijd_id; //unieke sleutel

	private static final long serialVersionUID = 1L;

	@OneToOne
	private Wedstrijd wedstrijd; 
	
	private String stadium;
	
    private int tickets; //aantal tickets beschikbaar

    public WedstrijdTicket(Wedstrijd wedstrijd, int tickets) {
        this.wedstrijd = wedstrijd;
        this.tickets = tickets;
    }
    

	public WedstrijdTicket() {
    }
    
    public int getTickets() {
        return tickets;
    }
    
    public Wedstrijd getWedstrijd() {
        return wedstrijd;
    }
    
    public String getStadium() {
		return stadium;
	}

	public void setStadium(String stadium) {
		this.stadium = stadium;
	}
    
    //We willen 'aantal' tickets kopen
    public int ticketsKopen(int aantal) {
        if (aantal <= 0) {
            return -1;
        }
        
        //Nog voldoende tickets
        if (tickets >= aantal) {
            tickets -= aantal;
            return aantal;
        }

        //Niet meer voldoende tickets
        int gekocht = tickets;
        tickets = 0;
        return gekocht;
    }

    public boolean uitverkocht() {
        return tickets == 0;
    }

	@Override
	public int hashCode() {
		return Objects.hash(wedstrijd_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WedstrijdTicket other = (WedstrijdTicket) obj;
		return Objects.equals(wedstrijd_id, other.wedstrijd_id);
	}
    
    
}
