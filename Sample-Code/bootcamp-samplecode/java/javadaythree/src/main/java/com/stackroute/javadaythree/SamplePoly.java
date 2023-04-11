package com.stackroute.javadaythree;

class Calculator
{
	
	//method overloading
	
	void add(int a,int b)

	{
		System.out.println( a+b);
	}
	

	
	void add(int a,float b)
	{
		System.out.println( a+b);
	}
	
	void add(float a,int b)
	{
		System.out.println( a+b);
	}
	
}


public class SamplePoly {

	public static void main(String[] args) {
 
new Calculator().add(23.1f,55);
	
//System.out.println(ans);

		new Calculator().add(20, 40);
		
		
	
	}

}
