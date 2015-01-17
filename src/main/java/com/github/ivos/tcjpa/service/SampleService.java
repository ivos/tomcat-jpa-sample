package com.github.ivos.tcjpa.service;

import java.util.List;
import java.util.Random;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.deltaspike.jpa.api.transaction.Transactional;

import com.github.ivos.tcjpa.persistence.Sample;
import com.github.ivos.tcjpa.persistence.SampleRepository;

/**
 * Sample service class. Implements the business logic.
 */
@ApplicationScoped
@Named("sampleService")
@Transactional
public class SampleService {

	@Inject
	private SampleRepository sampleRepository;

	public void createSample() {
		Sample sample = new Sample();
		String random = StringUtils.capitalize(RandomStringUtils
				.randomAlphabetic(3 + new Random().nextInt(12)).toLowerCase());
		sample.setValue(random);

		sampleRepository.save(sample);
	}

	public List<Sample> getSamples() {
		return sampleRepository.findAll();
	}

}
