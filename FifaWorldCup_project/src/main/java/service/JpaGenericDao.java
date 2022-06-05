package service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class JpaGenericDao<T> implements GenericDao<T> {

	
	private Class<T> type;
	protected EntityManager em;
	
	public JpaGenericDao(Class<T> type) {
		super();
		this.type = type;
	}
	
	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	@Transactional(readOnly = true)
	@Override
	public T get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional(readOnly = true)
	@Override
	public List<T> findAll() {
		return this.em.createQuery("select entity from " + this.type.getName() + " entity").getResultList();
	}

	@Transactional(readOnly = false)
	@Override
	public void insert(T object) {
		em.persist(object);		
	}

	@Transactional(readOnly = true)
	@Override
	public void delete(T object) {
		em.remove(em.merge(object));
	}

	@Transactional(readOnly = true)
	@Override
	public boolean exists(Long id) {
		return em.find(this.type, id) != null ? true : false;
	}
	

	@Override
	public T update(T object) {
		return em.merge(object);
	}

}
