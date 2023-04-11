package com.cgi.medication.service;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cgi.medication.exception.ViewAllFailedException;
import com.cgi.medication.model.Medication;
import com.cgi.medication.repository.MedicationRepo;


@Service
public class MedicationService implements MedicationSI{
	
	@Autowired
	MedicationRepo repo;
	
	

	@Override
	
	//TODO Change this limit after testing
	public Medication getAllMedications() throws ViewAllFailedException{
		RestTemplate rest = new RestTemplate();
		String url="https://api.fda.gov/drug/label.json?api_key=r7MXAHyln2R7KjtxdFLCWR2Qc7ysLR4ZZ6uiG4Ib&limit=50";
		 ResponseEntity<Medication> entity = null;
		 
		 entity=rest.exchange(url,HttpMethod.GET,getEntity(),Medication.class);
		 
		 repo.save(entity.getBody());
		 return entity.getBody();
		 
		
	}
	
	private HttpEntity getEntity() {
		HttpHeaders headers=new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<>(headers);
	}



	

}
