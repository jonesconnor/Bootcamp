package com.cgi.service;

import java.util.List;

import com.cgi.exception.InsuranceAlreadyExistException;
import com.cgi.exception.InsuranceNotFoundException;
import com.cgi.exception.PlanIdAlreadyExistException;
import com.cgi.exception.PlanIdNotFoundException;
import com.cgi.model.Insurance;
import com.cgi.model.Plan;

public interface InsuranceService {
	
	public Insurance addInsurance(Insurance newinsurance) throws InsuranceAlreadyExistException;
	
	public List<Insurance> getAllInsurance();
	
	public boolean deletePlan(String insuranceid, String planid) throws PlanIdNotFoundException, InsuranceNotFoundException;
	
	public List<Insurance> getByType(String type);
	
	public Plan getByPlan(String insuranceid, String planname);
	
	public Plan addPlan(String insuranceid, Plan plan) throws PlanIdAlreadyExistException, InsuranceNotFoundException;

}
