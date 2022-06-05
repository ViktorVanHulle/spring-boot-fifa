package domain;

import java.io.Serializable;
import java.text.DateFormatSymbols;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

//Een wedstrijd
@Entity
public class Wedstrijd implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int wedstrijd_id; //unieke sleutel

    private String landen; //2 landen van de wedstrijd
    
    @DateTimeFormat
    private Date dag; //dag van de wedstrijd

    private int uur; //uur van de wedstrijd

    public Wedstrijd() {
    }
    

    public Wedstrijd(int id, String landen, Date dag, int uur) {
        this.wedstrijd_id = id;
        this.landen = landen;
        this.dag = dag;
        this.uur = uur;
    }

    public int getId() {
        return wedstrijd_id;
    }

    public String getLanden() {
        return landen;
    }

    public Date getDag() {
        return dag;
    }
    
    public String getMonthForInt() {
    	int num = this.dag.getMonth();
        String month = "wrong";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        if (num >= 0 && num <= 11) {
            month = months[num];
        }
        return month;
    }

    public int getUur() {
        return uur;
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
		Wedstrijd other = (Wedstrijd) obj;
		return Objects.equals(wedstrijd_id, other.wedstrijd_id);
	}

	@Override
    public String toString()
    {
        return String.format("%s vs %s op %d-11", landen, landen, dag); 
    }
}
