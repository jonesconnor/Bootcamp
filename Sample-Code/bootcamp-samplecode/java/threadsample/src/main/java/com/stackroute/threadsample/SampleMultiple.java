package com.stackroute.threadsample;

class ReadData extends Thread
{
	 String name;
	 ReadData(String nm)
	 {
		 this.name=nm;
		 setName(this.name);
	 }
	 public void run()
	 {
		  for(int i=0;i<=5;i++)
		  {
			  System.out.println("Reading data " + this.name +  i );
//			  try {
//				Thread.sleep(2000);
//			} 
//			  catch (InterruptedException e) {
//				 
//			}
			  
		  }
		 
	 }
	
}

class MoviePlay extends Thread
{
	 String name;
	 MoviePlay(String nm)
	 {
		 this.name=nm;
		 setName(this.name);
	 }
	 public void run()
	 {
		  for(int i=0;i<=5;i++)
		  {
			  System.out.println("Playing Movie " + this.name +  i );
			  
//			  try {
//					Thread.sleep(2000);
//				} 
//				  catch (InterruptedException e) {
//					 
//				}
			  
		  }
		 
	 }
	
}
class PrintData extends Thread
{
	 String name;
	 PrintData(String nm)
	 {
		 this.name=nm;
		 setName(this.name);
	 }
	 public void run()
	 {
		  for(int i=0;i<=5;i++)
		  {
			  System.out.println("Printing data " + this.name +  i );
			  
		  }
		 
	 }
}

public class SampleMultiple {

	public static void main(String[] args) {
	 
		ReadData readobj=new ReadData("Reader Thread");
	//	readobj.setPriority(Thread.MIN_PRIORITY);
		readobj.start();
		
//		try {
//			readobj.join();
//		} 
//		catch (InterruptedException e) {
//			 
//			e.printStackTrace();
//		}
		
		MoviePlay movieobj=new MoviePlay("Movie player thread");
	//	readobj.setPriority(Thread.MAX_PRIORITY);
		movieobj.start();
//		
//		try {
//			movieobj.join();
//		} 
//		catch (InterruptedException e) {
//			 
//			e.printStackTrace();
//		}
		
		PrintData priobj=new PrintData("printer thread");
		
		System.out.println("Main method completed");
		

	}

}
