package com.stackroute.threadsample;

class Signal extends  Thread
{
	int status=3;
	public static final int green=2;
	public static final int red=1;
	public static final int stop=0;
	String location;
	
	Signal(String loca)
	{
		location=loca;
	}
	
	public synchronized void setStatus(int sta)
	{
	  	status=sta;
		   if (status==2)
			   notifyAll(); // runnable
	}
	
	public boolean checkStatus()
	{
		 System.out.println(" Active status is  " + status);
		 
		 while(status==1)
		 {	
			 
			 try {
				wait();  // blocked
			} 
			 catch (Exception e) {
			 
				
			}
		 }
		 
		 if(status==0)
			 return false;
		 
		 return true;
			 
	}
	public void run()
	{
		while(true)
		{
			System.out.println ("Signal Processing at location " + location);
			 if(!checkStatus())
				 break;
		}
	}
	
	
	
}


public class SampleWaitNotify {

	public static void main(String[] args) throws Exception {
		 
Signal signalobj1=new Signal("White Field");
Signal signalobj2=new Signal("Beach Road");

signalobj1.start();
	signalobj1.setStatus(Signal.red);
	
	Thread.sleep(5000);
//	signalobj2.start();
	
	signalobj1.setStatus(Signal.green);
	
	Thread.sleep(1000);
	
	
	signalobj1.setStatus(Signal.red);
	
		
	}

}
