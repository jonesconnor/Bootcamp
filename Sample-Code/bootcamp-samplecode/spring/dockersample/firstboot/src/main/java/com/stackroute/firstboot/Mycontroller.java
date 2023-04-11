package com.stackroute.firstboot;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("first")
public class Mycontroller {
	
	@GetMapping("/home")
	public ResponseEntity gethome()
	{
		return new ResponseEntity("hello welcome",HttpStatus.OK);
	}

	@GetMapping("/first")
	
	public ResponseEntity gethome1()
	{
		return new ResponseEntity("welcome",HttpStatus.OK);
	}
}
