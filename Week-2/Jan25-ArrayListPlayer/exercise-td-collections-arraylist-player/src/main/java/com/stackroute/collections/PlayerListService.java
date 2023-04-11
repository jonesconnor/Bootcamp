package com.stackroute.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayerListService {
	
	private List<Player> playerList;
	
	public PlayerListService() {
		playerList = new ArrayList<>();
	}
	
	public List<Player> getPlayerList() {
		return playerList;
	}
	
	public void addPlayersToList(Player p) {
		playerList.add(p);
	}
	
	public List<Player> getPlayerListSortedByNameIgnoringCaseInAscendingOrder() {
		Collections.sort(playerList, new PlayerNameComparator());
		return playerList;
	}
	
	public List<Player> getPlayerListSortedByScoreInDescendingOrder() {
		Collections.sort(playerList, new PlayerScoreComparator());
		return playerList;
	}
	
	public List<Player> getPlayerListGreaterThanFiftySortedByScoreInDescendingOrder() {
		List<Player> scoreGreaterThanFifty = new ArrayList<>();
		for (Player p : playerList) {
			if (p.getPlayerScore() > 50) {
				scoreGreaterThanFifty.add(p);
			}
		}
		Collections.sort(scoreGreaterThanFifty, new PlayerScoreComparator());
		return scoreGreaterThanFifty;
	}
	
	public List<Player> getPlayerListPlayedInSameMatchSortedByNameInAscendingOrder(String playerMatch) {
		List<Player> playersInSameMatch = new ArrayList<>();
		for (Player p : playerList) {
			if (p.getPlayerMatch().equals(playerMatch)) {
				playersInSameMatch.add(p);
			}
		}
		Collections.sort(playersInSameMatch, new PlayerNameComparator());
		return playersInSameMatch;
	}
}
