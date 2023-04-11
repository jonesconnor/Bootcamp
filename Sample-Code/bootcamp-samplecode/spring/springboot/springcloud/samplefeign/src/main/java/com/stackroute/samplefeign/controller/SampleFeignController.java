package com.stackroute.samplefeign.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.samplefeign.model.Medicine;
import com.stackroute.samplefeign.model.Patient;
import com.stackroute.samplefeign.service.FeignInter;
import com.stackroute.samplefeign.service.FeignPatient;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
public class SampleFeignController {
	
	@Autowired
	FeignInter pservice;
	
	@Autowired
	FeignPatient patientservice;
	
	@GetMapping("client/pharmacy/view")
	@CircuitBreaker(name="myservice",fallbackMethod="pharmacyFail")
	public ResponseEntity<List> getpharama()
	{
	List<Medicine> details=			pservice.getMedicines();
	return new ResponseEntity<List>(details,HttpStatus.OK);
	}
	
	@GetMapping("client/patient/view")
	public ResponseEntity<List> getpatients()
	{
	List<Patient> details=		patientservice.getPatients();
	return new ResponseEntity<List>(details,HttpStatus.OK);
	}

	
	
	
	public ResponseEntity<String> pharmacyFail(Exception e)
	{
		return new ResponseEntity<String>("Pharamacy service is down. Wait for sometime ",HttpStatus.OK);
	}
}
