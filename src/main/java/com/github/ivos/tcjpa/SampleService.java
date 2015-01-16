package com.github.ivos.tcjpa;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
@Named("sampleService")
public class SampleService {

	private EntityManagerFactory emf;

	private EntityManager em;

	public SampleService() {
		emf = Persistence.createEntityManagerFactory("sample");
	}

	public EntityManagerFactory getEmf() {
		return emf;
	}

	public void createSample() {
		em = emf.createEntityManager();
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
