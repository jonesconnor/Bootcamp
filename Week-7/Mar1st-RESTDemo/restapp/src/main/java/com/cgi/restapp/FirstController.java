package com.cgi.restapp;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@ComponentScan(basePackages="com.cgi.restapp")
public class FirstController {
	
	@GetMapping("/home")
	public ResponseEntity<String> gethome() {
		return new ResponseEntity<String>("Welcome back", HttpStatus.OK);
	}
	
}
