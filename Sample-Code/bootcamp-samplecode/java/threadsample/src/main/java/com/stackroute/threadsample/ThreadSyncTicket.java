package com.stackroute.threadsample;

class TicketMaster
{
	int ticketcount=10;
	
	public  void ticketProcess(String type, int qty)
	{
		
		synchronized(this)
		{
		  if (type.equals("Reservation"))
		  {
			  for (int i=1;i<=qty;i++)
			  {
				  ticketcount--;
				  System.out.println("Reservation Happening . Current count is " + ticketcount);
				  try {
					Thread.sleep(1000);
				  		}
				  catch (InterruptedException e) {
					 
				  		}
			  }
		  } //if
		  else if (type.equals("Cancellation"))
		  {
			  for (int i=1;i<=qty;i++)
			  {
				  ticketcount++;
				  System.out.println("Cancellation in process . current count is " + ticketcount);
				  try {
					Thread.sleep(1000);
				  		}
				  catch (InterruptedException e) {
					 
				  		}
			  }
		  }
		}
	}//ticketprocess meth
	} // class

class Reservation extends Thread
{
	TicketMaster ticketmaster;
	Reservation(TicketMaster tmaster)
	{
		ticketmaster=tmaster;
	}
	
	public void run()
	{
		ticketmaster.ticketProcess("Reservation", 3);
	}
}


class Cancellation extends Thread
{
	TicketMaster ticketmaster;
	Cancellation(TicketMaster tmaster)
	{
		ticketmaster=tmaster;
	}
	
	public void run()
	{
		ticketmaster.ticketProcess("Cancellation", 2);
	}
}





public class ThreadSyncTicket {

	public static void main(String[] args) {
	
		TicketMaster ticketobj=new TicketMaster();
		Reservation reservethread=new Reservation(ticketobj);
		Cancellation cancelthread=new Cancellation(ticketobj);
		
		reservethread.start();
		cancelthread.start();
	}

}











