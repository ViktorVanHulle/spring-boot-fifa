package service;

import java.util.List;


public interface GenericDao<T> {
	
	public T get(Long id);
	
	public List<T> findAll();
	
	public void insert(T object);
	
	public void delete(T object);
	
	public boolean exists(Long id);
	
	public T update(T object);

}