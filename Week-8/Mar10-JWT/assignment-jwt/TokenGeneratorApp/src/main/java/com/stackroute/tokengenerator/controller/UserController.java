package com.stackroute.tokengenerator.controller;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.tokengenerator.model.UserProfile;
import com.stackroute.tokengenerator.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class UserController {
	
	@Autowired
	UserService uservice;

	 //keep an endpoint named register ,which stores the data
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody UserProfile user) {
		UserProfile newUser = uservice.registerUser(user);
		return new ResponseEntity<UserProfile>(newUser, HttpStatus.CREATED);
	}
	
	// define an endpoint named login, which will return a token
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody UserProfile user) {
		boolean result = uservice.validateUser(user);
		if (result) {
			String token = generateToken(user);
			HashMap mymap=new HashMap();
			mymap.put("token", token);
			
			return new ResponseEntity<HashMap>(mymap,HttpStatus.OK);
		}
		return new ResponseEntity<String>("Invalid credentials", HttpStatus.UNAUTHORIZED);
	}
	
	private String generateToken(UserProfile userobj)
	{
		
		long expiry=10_000_00;
		
		return Jwts.builder().setSubject(userobj.getMailId()).setAudience(userobj.getMailId())
						.setIssuedAt(new Date(System.currentTimeMillis()))
						.setExpiration(new Date(System.currentTimeMillis()+ expiry))
						.signWith(SignatureAlgorithm.HS256, "authkey")
						.compact();
	}

	
	
}
