package com.cgi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgi.exception.InsuranceAlreadyExistException;
import com.cgi.exception.InsuranceNotFoundException;
import com.cgi.exception.PlanIdAlreadyExistException;
import com.cgi.exception.PlanIdNotFoundException;
import com.cgi.model.Insurance;
import com.cgi.model.Plan;
import com.cgi.service.InsuranceService;

@RestController
@RequestMapping("/insurance")
public class InsuranceController {
	
	private InsuranceService iservice;
	
	@Autowired
	public InsuranceController(InsuranceService iservice) {
		this.iservice = iservice;
	}
	
	@PostMapping("/addinsurance")
	public ResponseEntity<?> addinsurance(@RequestBody Insurance insurance) {
		Insurance result;
		try {
			result = iservice.addInsurance(insurance);
			return new ResponseEntity<Insurance>(result, HttpStatus.CREATED);
		} catch (InsuranceAlreadyExistException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
	@GetMapping("/allinsurance")
	public ResponseEntity<?> getAllInsurance() {
		List<Insurance> result = iservice.getAllInsurance();
		return new ResponseEntity<List<Insurance>>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("/{insuranceid}/{planid}")
	public ResponseEntity<?> deletePlan(@PathVariable("insuranceid") String insuranceid, @PathVariable("planid") String planid) {
		try {
			boolean result = iservice.deletePlan(insuranceid, planid);
			return new ResponseEntity<String>("Plan successfully deleted", HttpStatus.OK);
		} catch (PlanIdNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		} catch (InsuranceNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
	@GetMapping("/{type}")
	public ResponseEntity<?> getByType(@PathVariable("type") String type) {
		List<Insurance> result = iservice.getByType(type);
		return new ResponseEntity<List<Insurance>>(result, HttpStatus.OK);
	}
	
	@GetMapping("/{insuranceid}/{planname}")
	public ResponseEntity<?> getByPlanName(@PathVariable("insuranceid") String insuranceid, @PathVariable("planname") String planname) {
		Plan result = iservice.getByPlan(insuranceid, planname);
		return new ResponseEntity<Plan>(result, HttpStatus.OK);
	}
	
	@PostMapping("/{insuranceid}")
	public ResponseEntity<?> addPlan(@PathVariable("insuranceid") String insuranceid, @RequestBody Plan plan) {
		try {
			Plan result = iservice.addPlan(insuranceid, plan);
			return new ResponseEntity<Plan>(result, HttpStatus.CREATED);
		} catch (PlanIdAlreadyExistException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		} catch (InsuranceNotFoundException e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
}
