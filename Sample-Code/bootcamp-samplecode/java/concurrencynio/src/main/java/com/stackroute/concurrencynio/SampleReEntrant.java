package com.stackroute.concurrencynio;

import java.util.concurrent.locks.ReentrantReadWriteLock;

class UserData extends Thread
{
static	ReentrantReadWriteLock readwrite=new ReentrantReadWriteLock();
	public void run()
	{
		   try
		   {
			   	writeData();
			   	 sleep(2000);
			   	 readData();
		   }
		catch(Exception e) {}
		
		
		
	}
	public void writeData()
	{
		     readwrite.writeLock().lock();
		     try
		     {
		    	 System.out.println("writing inside file");
		     }
		     catch(Exception e) {}
		     readwrite.writeLock().unlock();
	
	}
	
	public void readData()
	{
		readwrite.readLock().lock();
		
		try
		{
			System.out.println("reading from file");
			
			
		}
		catch (Exception e)  {}
		
		readwrite.readLock().unlock();
	}
	
}

public class SampleReEntrant {

	public static void main(String[] afrgs) {
	 
		
		UserData data=new UserData();
		
		data.start();
		

		UserData data1=new UserData();
		data1.start();
	}

}
