package com.cgi.medication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cgi.medication.exception.ViewAllFailedException;
import com.cgi.medication.model.Medication;
import com.cgi.medication.service.MedicationService;

@RestController
@CrossOrigin
public class MedicationController {
	
	@Autowired
	MedicationService serve;
	
	@GetMapping("/viewall")
	public ResponseEntity<?> viewall(){
		Medication res;
		try {
			res = serve.getAllMedications();
		} catch (ViewAllFailedException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.METHOD_FAILURE);
		}
		return new ResponseEntity<Medication>(res, HttpStatus.OK);
	}
	

}
