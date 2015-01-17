package com.github.ivos.tcjpa.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManagerFactory;

import com.github.ivos.tcjpa.config.EntityManagerProducer;

/**
 * Exposes the {@link EntityManagerProducer} to be shown on the JSP page.
 */
@ApplicationScoped
@Named("entityManagerBean")
public class EntityManagerBean {

	@Inject
	private EntityManagerProducer entityManagerProducer;

	public EntityManagerFactory getEmf() {
		return entityManagerProducer.getEmf();
	}

}
