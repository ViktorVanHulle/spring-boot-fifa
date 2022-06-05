package service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import domain.WedstrijdTicket;

@Repository("voetbalDao")
public class JpaVoetbalDao extends JpaGenericDao<WedstrijdTicket> implements VoetbalDao {
	
	public JpaVoetbalDao() {
		super(WedstrijdTicket.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	@Transactional(readOnly = true)
	public List<WedstrijdTicket> getWedstrijdenByStadium(String stadium) {
		// TODO Auto-generated method stub
		TypedQuery<WedstrijdTicket> query = em
				.createNamedQuery("WedstrijdTicket.getWedstrijdenByStadium", WedstrijdTicket.class);
		query.setParameter("stadium", stadium);
		return query.getResultList();
	}

	@Override
	@Transactional(readOnly = true)
	public WedstrijdTicket getWedstrijd(int id) {
		TypedQuery<WedstrijdTicket> queryWedstrijd = em
				.createNamedQuery("WedstrijdTicket.getWedstrijd", WedstrijdTicket.class);
		queryWedstrijd.setParameter("id", id);
		return queryWedstrijd.getSingleResult();
	}

	@Override
	public WedstrijdTicket update(WedstrijdTicket object) {
		// TODO Auto-generated method stub
		return super.update(object);
	}

	@Override
	public String[] getStadiumList() {
		List<String> stadiums = new ArrayList<String>();
		        
		super.findAll().forEach(w -> {
			if(!stadiums.contains(w.getStadium())) stadiums.add(w.getStadium());
		});
		
        String[] str = new String[stadiums.size()];
		
	    for (int i = 0; i < stadiums.size(); i++) {
	    	str[i] = stadiums.get(i);
	    }
	 		
		return str;
	}

	
	


}
