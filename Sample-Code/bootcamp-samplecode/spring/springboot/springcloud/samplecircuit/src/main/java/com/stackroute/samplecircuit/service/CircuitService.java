package com.stackroute.samplecircuit.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CircuitService {

	
	public String getPharmacyData()
	{
		RestTemplate rest=new RestTemplate();
		String url="http://localhost:9092/pharma/viewAllDetails";
		
	ResponseEntity<String> resentity=null;
		
		resentity=rest.exchange(url,HttpMethod.GET,getEntity(),String.class );
		
		
		return resentity.getBody().toString();
		
	 }
	
	
	private HttpEntity getEntity()
	{
		HttpHeaders headers=new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
	// headers.set("Authorization")
		return new HttpEntity<>(headers);
	}
}
