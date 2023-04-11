package com.stackroute.consumer;

public class Team {
 
	 @Override
	public String toString() {
		return "Team [teamid=" + teamid + ", teamname=" + teamname + ", country=" + country + "]";
	}
	String teamid;
	
	 
	 String teamname;
 
	 String country;
	 

public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getTeamname() {
		return teamname;
	}
	public void setTeamname(String teamname) {
		this.teamname = teamname;
	}
	public String getTeamid() {
		return teamid;
	}
	public void setTeamid(String teamid) {
		this.teamid = teamid;
	}
	 
	
}

