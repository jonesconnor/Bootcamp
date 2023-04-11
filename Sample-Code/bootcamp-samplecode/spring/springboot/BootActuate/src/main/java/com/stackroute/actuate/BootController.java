package com.stackroute.actuate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BootController {
	
	@GetMapping("/welcome")
	public ResponseEntity<?> gethome()
	{
		return new ResponseEntity<String>("Welcome back",HttpStatus.OK);
	}

}
