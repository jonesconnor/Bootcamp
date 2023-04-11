package com.stackroute.designpattern;

interface iVisitor
{
	 Visitor getVisitor();
	 void getPatient();
	 
}

class Visitor 
{
	void details()
	{
		 System.out.println("name is Paul : timing 5 to 6pm");
	}
}

class Patient
{
	 void details()
	 {
		System.out.println("Patient name is Ruth. Room no : 45"); 
	 }
	 
}

class EntryDetails implements iVisitor
{

	
	//Visitor visitor;
	public Visitor getVisitor() {				//factory
		// TODO Auto-generated method stub
		return new Visitor(); 
	}

	public void getPatient() {		
		//facade
		Patient patient=new Patient();
		patient.details();
		
	}
	
	
}

public class SampleFacade {

	public static void main(String[] args) {
	
		iVisitor visitorobj=new EntryDetails();
		
	Visitor obj=	visitorobj.getVisitor();
	obj.details();
	
	visitorobj.getPatient();

	}

}
