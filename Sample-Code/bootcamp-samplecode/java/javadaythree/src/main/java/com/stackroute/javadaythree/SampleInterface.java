package com.stackroute.javadaythree;

public class SampleInterface {

	public static void main(String[] args) {
	
		iPrintable printobj;
		
	//	printobj=new AdminOffice();
		
	//	printobj=new MyProgram();
		
		printobj=new HallTicket();
	   // printobj.printData();
		
		printingAtClient(printobj);
	 
		
	}
	
	static void printingAtClient(iPrintable pobj)
	{
		
		if (pobj instanceof HallTicket)
		{
			HallTicket hallobj=(HallTicket) pobj;
			hallobj.ticketProcess();
			
		}
		else if (pobj instanceof AdminOffice )
		{
			AdminOffice office=(AdminOffice) pobj;
			office.adminDetail();
		}
		
		pobj.printData();
		
		
		
	}
	
//	static void printHallTicker(HallTicket ticket)
//	{
//		
//	}
//	
//	static void printOfficedetail(AdminOffice admin)
//	{
//		
//	}

}
