package com.stackroute.javadaythree;

public class HallTicket implements iPrintable{

	String name;
	String date;
	
	public void printData() {
		 
		System.out.println("Hall ticket  : " + name );
		
	}

	 public void ticketProcess()
	 {
		 System.out.println("Tickets will be issued on ...");
	 }
	
}
