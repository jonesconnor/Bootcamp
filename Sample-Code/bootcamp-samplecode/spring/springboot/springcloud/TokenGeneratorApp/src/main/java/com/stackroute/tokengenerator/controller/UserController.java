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
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService uservice;
	
	@PostMapping("/register")
	public ResponseEntity<?> registeruser(@RequestBody UserProfile user)
	{
		UserProfile uprofile=		uservice.registerUser(user);
		return new ResponseEntity<UserProfile>(uprofile,HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> loginuser(@RequestBody UserProfile user)
	{
		
		boolean result=		uservice.validateUser(user);
		
		if (result)
		{
			String mytoken=generateToken(user);
			HashMap mymap=new HashMap();
			mymap.put("token",mytoken);
			
			return new ResponseEntity<HashMap>(mymap,HttpStatus.OK);
			
		}
		else
		{
			return new ResponseEntity<String>("Invalid Credentials",HttpStatus.UNAUTHORIZED);
		}
		
	}
	
	
	private String generateToken(UserProfile userobj)
	{
		
		long expiry=10_000_00;
		
		return Jwts.builder().setSubject(userobj.getUsername()).setAudience(userobj.getMailid())
						.setIssuedAt(new Date(System.currentTimeMillis()))
						.setExpiration(new Date(System.currentTimeMillis()+ expiry))
						.signWith(SignatureAlgorithm.HS256, "cgicanada23")
						.compact();
						
		
		
	}
	
	
}
