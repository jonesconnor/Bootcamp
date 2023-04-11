package com.stackroute.collections;

import java.util.Comparator;

public class PlayerScoreComparator implements Comparator<Player>{
	@Override
	public int compare(Player p1, Player p2) {
		int scoreComparison = Integer.compare(p2.getPlayerScore(), p1.getPlayerScore());
		if (scoreComparison == 0) {
			return Integer.compare(p1.getPlayerId(), p2.getPlayerId());
		}
		return scoreComparison;
	}
}
