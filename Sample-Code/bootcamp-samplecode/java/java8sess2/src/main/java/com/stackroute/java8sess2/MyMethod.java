package com.stackroute.java8sess2;

public class MyMethod {
	
	 public int squareIt(int a)
	 {
		 return a*a;
	 }

	 public String displayUcase(String input)
	 {
		 return input.toUpperCase();
	 }
	 
	 public static boolean checkData(int a,int b)
	 {
		 return a>b;
	 }
	 
	 public void printDetails(int n,String data)
	 {
		 for(int i=1;i<=n;i++)
			 System.out.println(data);
	 }
	 
	 
}
