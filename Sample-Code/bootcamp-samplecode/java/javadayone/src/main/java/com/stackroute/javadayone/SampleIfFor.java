package com.stackroute.javadayone;

import java.util.Scanner;

public class SampleIfFor {
	public static void main(String ar[])
	{
		
		String studentName;
		int noofdays;
		
		Scanner scan=new Scanner(System.in);
		
		System.out.println ("enter name ");
		
		studentName=scan.next();
		
		System.out.println ("enter numberof days of leave");
		
		noofdays=scan.nextInt();
		
		if ( (studentName!=null) && (noofdays>=0) )
		{
			
			int weeks=noofdays/7;
			
			int days=noofdays%7;
			
			 
			  if(weeks>=4)
				 System.out.println("Sorry. you are dropped out of this Academy");
			 else if(weeks>=2)
				 System.out.println(" Hai " + studentName + " please bring your parents");
			 else
				 System.out.println("You are absent for " + weeks + " weeks  " + days  + " Days ");
			 
			
			
			
		}
	
		
		
		
		
		
		
		
		
		
	}

}
