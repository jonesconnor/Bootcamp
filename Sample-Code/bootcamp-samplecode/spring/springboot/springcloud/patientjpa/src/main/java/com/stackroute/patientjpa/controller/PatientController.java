package com.stackroute.patientjpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.stackroute.patientjpa.exception.PatientAlreadyExistException;
import com.stackroute.patientjpa.exception.PatientIdDoesNotExistException;
import com.stackroute.patientjpa.model.Patient;
import com.stackroute.patientjpa.service.PatientService;

@RestController
@RequestMapping("patient")
@CrossOrigin
public class PatientController {
	
	@Autowired
	PatientService pservice;
	
	@PostMapping("/addpatient")
	public ResponseEntity<?> addpatient(@RequestBody Patient newpatient)
	{
		
	try {
		Patient patadded=pservice.addPatient(newpatient);
		return new ResponseEntity<Patient>(patadded,HttpStatus.CREATED);
		
	} 
	catch (PatientAlreadyExistException e) {
	return new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT );
	
	}
		
		
	}
	
	@GetMapping("/viewall")
    public ResponseEntity<?> viewpatients()
    {
		
		List<Patient> patients = pservice.viewAllPatients();
		
		return new ResponseEntity<List>(patients,HttpStatus.OK);
		
    }
	
	@DeleteMapping("/delete/{patientid}")
	
	public ResponseEntity<?> deleteRec(@PathVariable("patientid") String patid)
	{
		
	try {
		boolean result=		pservice.deletePatient(Integer.parseInt(patid));
		return new ResponseEntity<String>("PAtiend record deleted successfully" , HttpStatus.OK);
	} 
	catch (NumberFormatException e) {
		 return new ResponseEntity<String>("patiend id should be number",HttpStatus.NOT_FOUND);
	} 
	
	catch (PatientIdDoesNotExistException e) {
		 
		return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
	}
		
		
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updatedPatient(@RequestBody Patient patientoupd)
	{
		
	try {
		Patient resultobj=pservice.updatePatient(patientoupd);
		return new ResponseEntity<Patient>(resultobj , HttpStatus.OK);
		
	} 
	catch (PatientIdDoesNotExistException e)
	{
		return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
	}
		
	}
	
	
	@GetMapping("/viewbyName/{pname}")
	
	public ResponseEntity<?> viewByname(@PathVariable("pname") String patname)
	{
		
	List<Patient> patientlist=	pservice.getPatientByName(patname);
	return new ResponseEntity<List>(patientlist,HttpStatus.OK);
	
	}
	
	@GetMapping("viewidlesser/{id}")
	
	public ResponseEntity<?> viewByLesspatid(@PathVariable("id") String patid)
	{
		
	List<Patient> patientlist=	pservice.getPatientIdLesstha(Integer.parseInt(patid));
	return new ResponseEntity<List>(patientlist,HttpStatus.OK);
	
	}

	@GetMapping("/pharamacy/getData") 
	 public ResponseEntity<?> getpharamacydata()
	 {
		RestTemplate resttemp=new RestTemplate();;
		
		
		String url="http://localhost:9092/pharma/viewAllDetails";
		
		ResponseEntity<String> resentity=null;
		
		resentity=resttemp.exchange(url,HttpMethod.GET,getEntity(),String.class );
		
		
		return new ResponseEntity<String>(resentity.getBody(),HttpStatus.OK);
		
	 }
	
	
	private HttpEntity getEntity()
	{
		HttpHeaders headers=new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
	// headers.set("Authorization")
		return new HttpEntity<>(headers);
	}
	
	
	
	
}
