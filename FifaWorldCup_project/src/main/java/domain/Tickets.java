package domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class Tickets {
	
	private int value;
    
    @NotEmpty(message="{validation.NotEmpty.message}")
    @Email
	private String email;
    
	private int voetbalcode1;
    
	private int voetbalcode2;


	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getVoetbalcode1() {
		return voetbalcode1;
	}

	public void setVoetbalcode1(int voetbalcode1) {
		this.voetbalcode1 = voetbalcode1;
	}

	public int getVoetbalcode2() {
		return voetbalcode2;
	}

	public void setVoetbalcode2(int voetbalcode2) {
		this.voetbalcode2 = voetbalcode2;
	}
	
	
	
	
	
}
