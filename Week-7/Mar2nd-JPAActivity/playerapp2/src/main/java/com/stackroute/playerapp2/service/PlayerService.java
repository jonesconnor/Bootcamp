package com.stackroute.playerapp2.service;

import java.util.List;

import com.stackroute.playerapp2.model.Player;

public interface PlayerService {
	
	Player addPlayer(Player player);
	List<Player> viewPlayers();
	
}
