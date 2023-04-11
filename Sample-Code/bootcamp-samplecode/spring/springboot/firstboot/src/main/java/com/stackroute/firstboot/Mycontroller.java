package com.stackroute.firstboot;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Mycontroller {
	
	@GetMapping("/home")
	public ResponseEntity gethome()
	{
		return new ResponseEntity("hello",HttpStatus.OK);
	}

	@GetMapping("/first")
	
	public ResponseEntity gethome1()
	{
		return new ResponseEntity("welcome",HttpStatus.OK);
	}
}
