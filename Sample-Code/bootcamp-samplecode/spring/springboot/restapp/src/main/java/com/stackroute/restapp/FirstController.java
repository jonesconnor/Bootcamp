package com.stackroute.restapp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {
	
	@Value("${company.name}")
String company;
	
	@GetMapping("/home")
	public ResponseEntity gethome()
	{
		return new ResponseEntity("Welcome back " + company , HttpStatus.OK);
	}

}
