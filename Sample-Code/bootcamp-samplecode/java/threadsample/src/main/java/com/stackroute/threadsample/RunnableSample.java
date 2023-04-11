package com.stackroute.threadsample;

class Engine
{
	String type;
}

class Car extends Engine implements Runnable
{
	volatile int  choice;
	int count;
	void setChoice(int c)
	{
		choice=c;
	}
	public void run() {
	    
		while(choice==0)
		{
			count++;
			
			
		}
		
		System.out.println ("Car is running , choice is " + choice + " count " + count);
		
	}
   	

}

public class RunnableSample {

	public static void main(String[] args) throws Exception {
	 
		System.out.println("Before setting choice ");
		Car carobj=new Car();
		
		new Thread(carobj).start();
		 Thread.sleep(1000);
			System.out.println("After  setting choice ");
			
			carobj.setChoice(1);
	}

}
