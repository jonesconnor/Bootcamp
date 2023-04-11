package com.stackroute.java8sess1.fis;

class Player
{
	  String playername;
	  int age;
	  String country;
	public String getPlayername() {
		return playername;
	}
	public void setPlayername(String playername) {
		this.playername = playername;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	} 
	
}


interface iPlayer
{
	   void showDetails(Player player);
	
}
public class LamdbaFunctioncall {
	static void playerDetails(Player player,iPlayer iplayer,String day)
	{
		
	   if (!day.equals("Sunday"))
	   {
		   iplayer.showDetails(player);
	   }
	   else
		   System.out.println("No matches scheduled");
	}	
	public static void main(String[] args) {
		
		Player player1=new Player();
		player1.setPlayername("Ronald");
		player1.setAge(40);
		player1.setCountry("Aus");
		
		iPlayer iplay1=(pla)->System.out.println("Player name is  " + pla.getPlayername());
		
		
		
		
		//playerDetails(player1, iplay1, "Sunday");
		
	playerDetails(player1,(pla)->System.out.println("Player name is  " + pla.getPlayername()),"Monday");
	
	playerDetails(player1,(pla)->System.out.println("details are " + pla.getPlayername() + "country " + pla.getCountry()),"Tuesday");
	
	
	}

}
