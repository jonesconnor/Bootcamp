package com.stackroute.javadaytwo;

public class Recuritment {
	
	   String role;
		static int count;
	   
	
		
		static
		{
			count=0;
		}
	   
	   public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	  int newRecruitment()
	  {
		 return 	++count;
	  }
	  
	  int termination()
	  {
		 return --count;
	  }
	  
	  public static void displayDetails()
	  {
		  System.out.println( " Current #of Employee is " + count);
	  }
	

}
