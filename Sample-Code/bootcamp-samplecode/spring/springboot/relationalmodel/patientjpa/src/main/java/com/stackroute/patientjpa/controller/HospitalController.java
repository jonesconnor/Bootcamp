package com.stackroute.patientjpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.patientjpa.model.Hospital;
import com.stackroute.patientjpa.service.HospitalService;

@RestController
public class HospitalController {
	
	@Autowired
	HospitalService hservice;
	
	
	@PostMapping("/hospital/add")
	public ResponseEntity addhospital(@RequestBody Hospital hospital)
	{
		Hospital result=hservice.addHospital(hospital);
		
		return new ResponseEntity<Hospital>(result,HttpStatus.CREATED);
		
	}
	
	@GetMapping("hospital/view")
	public ResponseEntity viewhosptial()
	{
		
		List<Hospital> list=hservice.viewHospital();
	return new ResponseEntity<List>(list,HttpStatus.OK);
	}
	

}
