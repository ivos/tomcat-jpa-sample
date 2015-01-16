package com.github.ivos.tcjpa;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.apache.deltaspike.jpa.api.transaction.Transactional;

@RequestScoped
@Named("sampleService")
public class SampleService {

	@Inject
	private EntityManager em;

	public EntityManagerFactory getEmf() {
		return em.getEntityManagerFactory();
	}

	@Transactional
	public void createSample() {
		Sample newSample = new Sample();
		em.persist(newSample);
	}

	@Transactional(readOnly = true)
	public List<Sample> getSamples() {
		return em.createQuery("select s from Sample s", Sample.class)
				.getResultList();
	}

}
