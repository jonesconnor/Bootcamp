package com.stackroute.collections;

public class Player {
	
	private int playerId;
	private String playerName;
	private int playerScore;
	private String playerMatch;
	
	public Player(int playerId, String playerName, int playerScore, String playerMatch) {
		setPlayerId(playerId);
		setPlayerName(playerName);
		setPlayerScore(playerScore);
		setPlayerMatch(playerMatch);
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public int getPlayerScore() {
		return playerScore;
	}

	public void setPlayerScore(int playerScore) {
		this.playerScore = playerScore;
	}

	public String getPlayerMatch() {
		return playerMatch;
	}

	public void setPlayerMatch(String playerMatch) {
		this.playerMatch = playerMatch;
	}
	
	@Override
	public String toString() {
		return "playerId=" + getPlayerId() + ", playerName=" + getPlayerName() + ", playerScore=" + getPlayerScore() + ", playerMatch=" + getPlayerMatch();
	}
	
}
