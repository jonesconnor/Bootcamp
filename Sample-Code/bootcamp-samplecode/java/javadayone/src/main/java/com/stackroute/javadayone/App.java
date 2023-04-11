package com.stackroute.javadayone;

class Light extends Thread
{
	 int status=3;
	  public static final int switchon=2;
	 public static final int swtichoff=0;
	 String company;
	 Light(String n)
	 {
		 company=n;
	 }
	 public synchronized void setStatus(int a)
	 {
		 status=a;
		 if(status==2)
			 notify();
	 }
	 
	 public boolean checkStatus()
	 {
		 System.out.println("Status is " + status);
		 while(status==1)
		 {
			   try
			   {
				   wait();
			   }
			   catch(Exception e) { }
		 }
		 if(status==0)
			 return false;
		 
		 return true;
		 
		 
	 }
	  public void run()
	  { 
		  while(true)
		  {
			   System.out.println("Company name " + company);
			   if(!checkStatus())
				   break;
		  }
		  
	  }
	 
}
public class App 
{
  

    	public static void main(String[] args) throws Exception {
    		Light tube =new Light("Crompton");
    		Light nightlamp=new Light("Surya");
    		
    		
    		tube.start();
    		tube.setStatus(1);
    		Thread.sleep(5000);
    		
    	 	nightlamp.start();
    		  	
    		nightlamp.setStatus(Light.swtichoff);
    		//tube.setStatus(Light.switchon);
    		
    		//Thread.sleep(1000);
    		 //tube.setStatus(1);
    		
    		tube.setStatus(Light.swtichoff);
    		
    		
    }
}
