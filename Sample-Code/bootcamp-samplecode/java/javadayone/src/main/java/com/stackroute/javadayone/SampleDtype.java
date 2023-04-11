package com.stackroute.javadayone;

public class SampleDtype {

	public static void main(String[] args) {
		 
		
		
		String name="Mary";
		
		float mathsscore=98.5f;
		double physicscore=98.5;
		
		
		boolean flag= (mathsscore==physicscore);
		
		System.out.println(flag);
	
	float csscore=(float) physicscore; //casting
	
	 Integer num1=new Integer(20);
	 
	 Integer num2=30; // autoboxing
	 int num3=num2;  //autounboxing
	 
	 System.out.println ( Integer.compare(45, 60) );
	 
	 
	 String age="10a";
	 
//	 int agevalue=Integer.parseInt(age);
	 
	 
//	 System.out.println(agevalue);
	
    String company="NIIT";
    
    company+=34;
    
    System.out.println(company);
    		
	  
	
	}
	
	

}
