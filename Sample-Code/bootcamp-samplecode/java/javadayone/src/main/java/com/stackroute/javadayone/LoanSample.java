package com.stackroute.javadayone;

import java.util.Scanner;

public class LoanSample {

	public static void main(String[] args) {
		Scanner scanobj=new Scanner(System.in);
		System.out.println("enter loan type");
			String loantype=scanobj.next();
			if (loantype.equalsIgnoreCase("Housing"))
			{
				
				     System.out.println("enter loan amount");
				       long loanamount=scanobj.nextLong();
				       int years = 5;
				     float monthlyamount= loanamount / ( 5*12 );
				     float intramount= (loanamount *10 /100)  / (5*12);
				     int repayment= (int)( monthlyamount+ intramount) ;
			 System.out.println("Monthly payment for " + loanamount + " year " + years + " is : rs. " + repayment );


	}

}
}
