package com.github.ivos.tcjpa;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.deltaspike.jpa.api.transaction.TransactionScoped;

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

}
