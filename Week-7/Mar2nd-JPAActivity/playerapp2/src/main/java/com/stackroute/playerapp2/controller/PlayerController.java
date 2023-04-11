package com.stackroute.playerapp2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.playerapp2.model.Player;
import com.stackroute.playerapp2.service.PlayerService;

@RestController
public class PlayerController {
	
	@Autowired
	PlayerService pservice;
	
	@GetMapping("/player/viewAll")
	public ResponseEntity<List<Player>> viewplayers() {
		List<Player> players = pservice.viewPlayers();
		return new ResponseEntity<List<Player>>(players,HttpStatus.OK);
	}
	
	@PostMapping("/player/addData")
	public ResponseEntity<?> addplayer(@RequestBody Player newplayer) {
		try {
			Player playeradded = pservice.addPlayer(newplayer);
			return new ResponseEntity<Player>(playeradded,HttpStatus.CREATED);
		} catch (RuntimeException e ) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
}
