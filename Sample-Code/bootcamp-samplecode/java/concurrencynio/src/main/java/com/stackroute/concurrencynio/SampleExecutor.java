package com.stackroute.concurrencynio;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Myprogram extends Thread
{
	  String program;
	  int num;
	    Myprogram(String p,int num)
	    {
	    	this.program=p;
	    	this.num=num;
	    }
	    
	public void run()
	{
		int sum=0;
		  if(program.equals("findsum"))
		  {
			   for (int i=0;i<=num;i++)
				  sum+=i;
			   
			  System.out.println("Sum of given " + num  + " numbers is " + sum);
		  }
		  
		  else if(program.equals("printstar"))
		  {
			   for(int i=0;i<=num;i++)
				   System.out.print("*");
			   
			   System.out.println("Star Printed---------");
			   
		  }
	}
	    
}



public class SampleExecutor {

	public static void main(String[] args) {
		 
	Myprogram program1=new Myprogram("findsum",6);
	Myprogram program2=new Myprogram("printstar",50);
		
		ExecutorService service=Executors.newCachedThreadPool();
		
		service.execute(program1);

		service.execute(program2);
		
		service.shutdown();
	}

}













