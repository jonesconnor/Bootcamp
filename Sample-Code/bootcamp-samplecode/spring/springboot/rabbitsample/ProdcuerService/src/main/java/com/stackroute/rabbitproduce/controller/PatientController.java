package com.stackroute.rabbitproduce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.rabbitproduce.model.Patient;
import com.stackroute.rabbitproduce.service.PatientService;

@RestController
public class PatientController {
	
	@Value("${app.message}")
	String msg;
	
	
	PatientService patservice;
	
	@Autowired
	public PatientController(PatientService pservice)
	{
		this.patservice=pservice;
	}
	
	
	@PostMapping("/patient/add")
	public String addpatient(@RequestBody Patient patient)
	{
		
		// write code for calling save method with repo for persistence
		
		patservice.send(patient);
		return msg;
		
		
	}

}
