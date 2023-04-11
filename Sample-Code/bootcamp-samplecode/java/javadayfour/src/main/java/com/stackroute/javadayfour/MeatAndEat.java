package com.stackroute.javadayfour;

public class MeatAndEat implements Cloneable {

	
	String location;
	
	int qty;
	
	
	  void displayBill()  throws InvalidAmountException
	  {
		  qty=10;
		    String testamount="0";
		  
		  int amt=Integer.parseInt(testamount);
		    
		    
		    if(amt<=0)
		    	throw new InvalidAmountException("Amount can not be -ve");
		    	
		    	
		  
		  
		  
		  try
		  {
		    String amount="0";
		    
		    
		    int average=amt/12;
		    
		    System.out.println("bill amount " + amt*qty );
		  
		  }
//		  catch(InvalidAmountException e)
//		  {
//			  System.out.println("inside userdefine excep catch" + e.getMessage());
//		  }
		  catch(ArithmeticException exception)
		  {
			  System.out.println("error occured" + exception.getMessage());
		  }
//		  catch(NumberFormatException exception)
//		  {
//			  System.out.println("error occured" + exception.getMessage());
//
//		  }
		  
//		  catch(Exception exception)
//		  {
//			  System.out.println("error occured" + exception.getMessage());
//
//		  }
		  finally
		  {
			  System.out.println("cleaned up");
		  }
 	  }
	
	
	
		MeatAndEat(String location)
		{
			this.location=location;
		}
	
	 public void showMenu()
	 {
		   System.out.println("Grilled chicken " + "Location is " + location);
		   
	 }
	 
	 protected Object clone() throws CloneNotSupportedException
	 {
		 return super.clone();
	 }

	 
	 public static void main(String ar[]) throws CloneNotSupportedException, InvalidAmountException
	 {
	
		 MeatAndEat hotel1=new MeatAndEat("Blore");
		 
		 
		 System.out.println(hotel1.hashCode());
		 
		 MeatAndEat hotel2 = (MeatAndEat) hotel1.clone();
		 
		 // hotel2=hotel1;
		 
		 System.out.println(hotel2.hashCode());

		 
 	 hotel1.displayBill();
		 
	 hotel2.showMenu();	 
		 
	 
	System.out.println("Thank you. visit again"); 
		 
	 }
	 
	 
}







