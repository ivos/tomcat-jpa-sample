package com.github.ivos.tcjpa.persistence;

import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

/**
 * Sample repository.
 */
@Repository
public interface SampleRepository extends EntityRepository<Sample, Long> {
}
