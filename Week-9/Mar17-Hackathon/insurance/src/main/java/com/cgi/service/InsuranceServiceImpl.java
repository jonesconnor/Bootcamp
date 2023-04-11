package com.cgi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgi.exception.InsuranceAlreadyExistException;
import com.cgi.exception.InsuranceNotFoundException;
import com.cgi.exception.PlanIdAlreadyExistException;
import com.cgi.exception.PlanIdNotFoundException;
import com.cgi.model.Insurance;
import com.cgi.model.Plan;
import com.cgi.repository.InsuranceRepository;

@Service
public class InsuranceServiceImpl implements InsuranceService{
	
	
	@Autowired
	InsuranceRepository insurancerepo;
	
	@Override
	public Insurance addInsurance(Insurance newinsurance) throws InsuranceAlreadyExistException {
		Optional<Insurance> optinsurance = insurancerepo.findById(newinsurance.getInsuranceid());
		if (optinsurance.isEmpty()) {
			return insurancerepo.save(newinsurance);
		}
		throw new InsuranceAlreadyExistException("Insurance already exists");
	}
	
	@Override
	public List<Insurance> getAllInsurance() {
		return insurancerepo.findAll();
	}
	
	public boolean deletePlan(String insuranceid, String planid) throws PlanIdNotFoundException, InsuranceNotFoundException {
		Optional<Insurance> optinsurance = insurancerepo.findById(insuranceid);
		if (optinsurance.isPresent()) {
			Insurance insurance = optinsurance.get();
			List<Plan> plans = insurance.getPlans();
			boolean planRemoved = plans.removeIf(p -> p.getPlanid().equals(planid));
	        if (planRemoved) {
	            insurancerepo.save(insurance);
	            return true;
	        } else {
	            throw new PlanIdNotFoundException("Plan with ID " + planid + " not found in insurance with ID " + insuranceid);
	        }
		} else {
            throw new InsuranceNotFoundException("Insurance with ID " + insuranceid + " not found");
        }
	}
	
	public List<Insurance> getByType(String type) {
		return insurancerepo.findByType(type);
	}
	
	public Plan getByPlan(String insuranceid, String planname) {
		return insurancerepo.findByInsuranceidAndPlanname(insuranceid, planname);
	}
	
	public Plan addPlan(String insuranceid, Plan plan) throws PlanIdAlreadyExistException, InsuranceNotFoundException {
		Optional<Insurance> optInsurance = insurancerepo.findById(insuranceid);
	    if (optInsurance.isPresent()) {
	        Insurance insurance = optInsurance.get();
	        List<Plan> plans = insurance.getPlans();
	        for (Plan p : plans) {
	            if (p.getPlanid().equals(plan.getPlanid())) {
	                throw new PlanIdAlreadyExistException("Plan with ID " + plan.getPlanid() + " already exists");
	            }
	        }
	        plans.add(plan);
	        insurancerepo.save(insurance);
	        return plan;
	    } else {
	        throw new InsuranceNotFoundException("Insurance with ID " + insuranceid + " not found");
	    }
	}
}
