package com.cgi.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cgi.model.Insurance;
import com.cgi.model.Plan;

public interface InsuranceRepository extends MongoRepository<Insurance, String>{
	
	List<Insurance> findByType(String type);
	
	Plan findByInsuranceidAndPlanname(String insuranceid, String planname);

}
