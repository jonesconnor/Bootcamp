package com.cgi.firstboot;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="home")
public class MyController {
	
	@GetMapping("/new")
	public ResponseEntity<String> getHome() {
		return new ResponseEntity<String>("Welcome to Spring Boot", HttpStatus.OK);
	}
	
}
