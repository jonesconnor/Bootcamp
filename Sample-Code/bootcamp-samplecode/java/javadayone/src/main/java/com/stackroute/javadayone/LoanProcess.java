package com.stackroute.javadayone;

import java.util.Scanner;

public class LoanProcess {

	Scanner scan=new Scanner(System.in);
	
	public static void main(String[] args) {
		
		String customerName="";
		String flag="yes";
       Scanner scannew=new Scanner(System.in);
       
      // System.out.println()
		
   do
	{
		
			
		System.out.println("1-new , 2-show interest 3-exit");
		int choice = scannew.nextInt();
		
		
		  switch(choice)
		  {
		  case 1:
			  customerName=new LoanProcess().newCustomer();
			  break;
		  case 2:
			float result=new LoanProcess().processInterest(50000, customerName);
			System.out.println("interest for 5000 " + result);
			break;
			
		  case 3:
			  flag="no";
			  break;
			  
		  }
		
		  
		
		
	}    while(flag.equals("yes"));

	}
	
	
	String newCustomer()
	{
		
		System.out.println("Enter the customer Name");
		   return scan.next();
		   
	}
	
	
	float processInterest(int amount,String custname)
	{
		if (validateData(custname))
		{
			return amount*5/100;
		}
		
		return 0.0f;
		 
	}
	
	boolean validateData(String custname)
	{
		   if(custname.length()<3)
			   return false;
		   else
			   
			  return true;
		
	}
	
	
	
	

}
