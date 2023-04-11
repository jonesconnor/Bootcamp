package com.stackroute.solidprinci;

class Admin
{
	   Inpatient getInpatient()
	  {
		  return new Inpatient();
	  }
	   
	  Outpatient getOutpatient()
	  {
		  return new Outpatient();
	  }
	   void allotEmployee()
	   {
		   System.out.println("reception : 5, medical : 5, doctor support staff : 10 ");
	   }
}


class Inpatient
{
	 void roomAllotment()
	 {
		 
	 }
	  void showInpatient()
	   {
		   System.out.println("total rooms : 12, occupied :12 ") ;
	   }
}

class Outpatient
{
	 void showOutPatient()
	   {
		   System.out.println("Visitors : 120 , dt : 24th Jan");
	   }
}

public class SampleSingle {
	
	
	public static void main(String ar[])
	{
		
		new Admin().getInpatient().roomAllotment();
		
		new Admin().getOutpatient().showOutPatient();
	}

}
