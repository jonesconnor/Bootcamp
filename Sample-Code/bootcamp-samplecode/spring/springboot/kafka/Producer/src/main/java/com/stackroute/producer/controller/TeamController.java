package com.stackroute.producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.producer.model.Team;
import com.stackroute.producer.service.TeamService;

@RestController
public class TeamController {
	
	@Autowired
	TeamService teamservice;
	
	@PostMapping("/add/publish")
	public ResponseEntity<?> add(@RequestBody Team team)
	{
		
		// code for service call which stores the data in dbase
		
	  Team team1=	teamservice.addTeam(team);
	  return new ResponseEntity<Team>(team1,HttpStatus.OK);
	}

}
