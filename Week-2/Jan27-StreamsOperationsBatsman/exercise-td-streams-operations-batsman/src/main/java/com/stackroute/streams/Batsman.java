package com.stackroute.streams;

public class Batsman {
	
	private String name;
	private int matchesPlayed;
	private int totalRuns;
	private int highestScore;
	private Country country;
	
	public Batsman() {
	}

	public Batsman(String name, int matchesPlayed, int totalRuns, int highestScore, Country country) {
		setName(name);
		setMatchesPlayed(matchesPlayed);
		setTotalRuns(totalRuns);
		setHighestScore(highestScore);
		setCountry(country);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMatchesPlayed() {
		return matchesPlayed;
	}

	public void setMatchesPlayed(int matchesPlayed) {
		this.matchesPlayed = matchesPlayed;
	}

	public int getTotalRuns() {
		return totalRuns;
	}

	public void setTotalRuns(int totalRuns) {
		this.totalRuns = totalRuns;
	}

	public int getHighestScore() {
		return highestScore;
	}

	public void setHighestScore(int highestScore) {
		this.highestScore = highestScore;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

}
