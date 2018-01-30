package com.rga.estimator2;

import org.springframework.data.repository.CrudRepository;

public interface ResourceRepository extends CrudRepository<Resource, Long> {
	
	
	Iterable<Resource> findByRateCard(String rateCard);

	Iterable<Resource>  findByPosition(String position);

	Resource findByPositionAndRateCard(String position, String santiago2017);

	Resource findByDepartmentAndPositionAndRateCard(String departmente, String position, String rateCard);

}
