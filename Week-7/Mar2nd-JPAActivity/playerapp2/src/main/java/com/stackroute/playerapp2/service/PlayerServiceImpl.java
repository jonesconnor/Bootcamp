package com.stackroute.playerapp2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.playerapp2.model.Player;
import com.stackroute.playerapp2.repository.PlayerRepo;

@Service
public class PlayerServiceImpl implements PlayerService{
	
	@Autowired
	PlayerRepo playerrepo;
	
	@Override
	public Player addPlayer(Player playerobj) throws RuntimeException {
		
		Player savedobj = null;
		
		Optional<Player> optplayerexists = playerrepo.findById(playerobj.getPlayerid());
		
		if(optplayerexists.isPresent()) {
			throw new RuntimeException("Player already exists");
		} else {
			savedobj = playerrepo.save(playerobj);
		}
		
		return savedobj;
		
	}
	
	@Override
	public List<Player> viewPlayers() {
		return playerrepo.findAll();
	}
	
}
