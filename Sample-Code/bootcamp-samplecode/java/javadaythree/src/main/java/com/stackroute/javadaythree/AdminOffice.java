package com.stackroute.javadaythree;

public class AdminOffice implements iPrintable{

		String location;
	
	public void printData() {
	 
		System.out.println("You can connect with network printers " + location);
	}

	public void adminDetail()
	{
		System.out.println("Details of Admin is.....");
	}
	
	
}
