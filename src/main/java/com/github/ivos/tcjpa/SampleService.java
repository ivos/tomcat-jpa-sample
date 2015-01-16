package com.github.ivos.tcjpa;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@RequestScoped
@Named("sampleService")
public class SampleService {

	@Inject
	private EntityManager em;

	public EntityManagerFactory getEmf() {
		return em.getEntityManagerFactory();
	}

	public void createSample() {
		em.getTransaction().begin();
		Sample newSample = new Sample();
		em.persist(newSample);
		em.getTransaction().commit();
	}

	public List<Sample> getSamples() {
		em.getTransaction().begin();
		List<Sample> samples = em.createQuery("select s from Sample s",
				Sample.class).getResultList();
		em.getTransaction().commit();
		return samples;
	}

	public void close() {
		em.close();
	}

}
