package com.cgi.authentication.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgi.authentication.exception.UserNotFoundException;
import com.cgi.authentication.model.User;
import com.cgi.authentication.service.AuthService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
	
	@Autowired
	AuthService service;
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody User user){
		try {
			User userobj = service.register(user);
			return new ResponseEntity<User>(userobj,HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("User Already Exists",HttpStatus.CONFLICT);
		}
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user){
		try {
			User userobj = service.login(user);
			String token = generateToken(userobj);
			HashMap<String,String> map = new HashMap<String,String>();
			map.put("token", token);
			return new ResponseEntity<HashMap<String,String>>(map,HttpStatus.ACCEPTED);
		}
		catch (UserNotFoundException e) {
			return new ResponseEntity<String>("Invalid Login",HttpStatus.NOT_FOUND);
		}
	}
	@PutMapping("/updatepassword/")
	public ResponseEntity<?> changePassword(@RequestBody Map<String,String> json){
		try {
			String mailid = json.get("mailid");
			String oldpwd =json.get("oldpwd");
			String newpwd=json.get("newpwd");
			User userobj = service.changePassword(mailid,oldpwd,newpwd);
			return new ResponseEntity<User>(userobj,HttpStatus.OK);
		}catch (UserNotFoundException e) {
			return new ResponseEntity<String>("Invalid User/Password Combination",HttpStatus.NOT_FOUND);
		}
	}
	@DeleteMapping("/delete/{mailid}")
	public ResponseEntity<?> deleteUser(@PathVariable("mailid")String mailid){
		try {
			service.deleteById(mailid);
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
		} catch (UserNotFoundException e) {
			return new ResponseEntity<String>("User Not Found",HttpStatus.NOT_FOUND);
		}
		
	}
	
	public String generateToken(User user) {
		byte[] secret ="Screepalin21051992".getBytes();
			return Jwts.builder().setId(user.getMailid())
					.setAudience("APIs")
					.claim("Role",  user.getRole())
					.claim("unique_name", user.getMailid())
					.setIssuer("Squad A")
					.setNotBefore(new Date(System.currentTimeMillis()))
					.setIssuedAt(new Date(System.currentTimeMillis()))
					.setExpiration(new Date(System.currentTimeMillis()+(60*60*1000)))
					.signWith(SignatureAlgorithm.HS256, secret)
					.setHeaderParam("typ", "JWT")
					.compact();
	}

}
