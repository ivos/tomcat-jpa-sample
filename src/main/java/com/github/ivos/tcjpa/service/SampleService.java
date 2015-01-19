package com.github.ivos.tcjpa.service;

import java.util.List;
import java.util.Random;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.deltaspike.jpa.api.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.ivos.tcjpa.persistence.Related;
import com.github.ivos.tcjpa.persistence.Sample;
import com.github.ivos.tcjpa.persistence.SampleRepository;

/**
 * Sample service class. Implements the business logic.
 */
@ApplicationScoped
@Named("sampleService")
@Transactional
public class SampleService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	private SampleRepository sampleRepository;

	public void createSample() {
		logger.info("Creating sample.");
		Sample sample = new Sample();
		sample.setValue(generateText());

		Related related = new Related();
		related.setValue(generateText());
		sample.setRelated(related);

		sampleRepository.save(sample);
		logger.info("Created sample {}.", sample);
	}

	private String generateText() {
		return StringUtils.capitalize(RandomStringUtils.randomAlphabetic(
				3 + new Random().nextInt(12)).toLowerCase());
	}

	public List<Sample> getSamples() {
		logger.info("Listing sample.");
		List<Sample> samples = sampleRepository.findAll();
		logger.info("Listed {} samples.", samples.size());
		return samples;
	}

}
