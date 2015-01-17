package com.github.ivos.tcjpa.config;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.deltaspike.jpa.api.transaction.TransactionScoped;

/**
 * Entity manager producer.
 * <p>
 * Creates transaction-scoped {@link EntityManager} in a non-JEE environment,
 * like Tomcat.
 */
@ApplicationScoped
public class EntityManagerProducer {

	private static final String PERSISTENCE_UNIT_NAME = "sample";

	private EntityManagerFactory emf;

	public EntityManagerProducer() {
		emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	}

	@Produces
	@TransactionScoped
	public EntityManager create() {
		return emf.createEntityManager();
	}

	public void close(@Disposes EntityManager em) {
		if (em.isOpen()) {
			em.close();
		}
	}

	// Remove this method in a standard project
	public EntityManagerFactory getEmf() {
		return emf;
	}

}
