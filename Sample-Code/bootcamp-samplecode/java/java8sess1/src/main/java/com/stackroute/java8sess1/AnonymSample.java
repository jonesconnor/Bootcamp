package com.stackroute.java8sess1;

// @FunctionalInterface
interface iReport
{
	
	//method signature
	
	//parameters? zero, return type? void 
	  void printData();
	
	  
	  default void showDetails()
	  {
		  System.out.println("This is my Report data");
	  }
	  default void changeVal()
		{
			System.out.println("Data changed");
		}
	
}


interface iData
{
	// signature : how many param - 1, datatype of param  string, return type string
	String storeData(String fname);
	default void dataDetail()
	{
		System.out.println("Data processing");
	}
	default void changeVal()
	{
		System.out.println("Data changed");
	}
}
public class AnonymSample {
	public static void main(String[] args) {
//	iReport reportobj=new iReport() 
//									{
//								public	void printData()
//									{
//										System.out.println("Hi welcome");
//									}
//									};
		//reportobj.printData();
	iReport reportobj2= ()-> System.out.println("Hello lambda");
	
	reportobj2.printData();
	iData dataobj = (file)-> {
							     if (file.equals("employee"))
							    	 return "taxfile";
							     else
							    	 return "recruitmentfile";
							};
		
					
	String result=dataobj.storeData("employee");	
	System.out.println("file tpe is " +result);
	
	}

}
