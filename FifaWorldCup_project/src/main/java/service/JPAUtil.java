package service;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	private final static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ID372414_G5");

	public static EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	private JPAUtil() {
	}
}
