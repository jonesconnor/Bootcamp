package com.stackroute.javadayone;

import java.util.Scanner;

public class PatternFor {

	public static void main(String[] args) {

		Scanner scan=new Scanner(System.in);

		System.out.println("enter the rows count ");
		
		int lines=scan.nextInt();
		String result="";
		
     for(int i=1,count=0;count<lines ;i+=2,count++)
     {
    	 result= result + i + " ";
    	 System.out.println(result);
     }
    	 
		
     
//     for(int i=0;;)
//     {
//    	 System.out.println("Welcome");
//     }
//     
     
     System.out.println("1"+  new Integer(3) + 2 );

		
	}

}
