package com.stackroute.samplecircuit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.samplecircuit.service.CircuitService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
public class CicuitController {
	
	@Autowired
	CircuitService service;
	
	@GetMapping("/circuit/pharma/view")
	@CircuitBreaker(name="myservice",fallbackMethod="pharmacyFail")
	public ResponseEntity<String> getview()
	{
		String result=		service.getPharmacyData();
		return new ResponseEntity<String>(result,HttpStatus.OK);
	}
	
	
	public ResponseEntity<String> pharmacyFail(Exception e)
	{
		return new ResponseEntity<String>("Pharamacy service is down. Wait for sometime ",HttpStatus.OK);
	}

}
