package com.stackroute.solidprinci;



  abstract class PatientAccount
{
	abstract void billing();
	  
	
}

class InsurancePatientAccount extends PatientAccount
{

	@Override
	void billing() {
	System.out.println("Bill processed by Insurance company");
		
	}
	
}
class PaymentAccount extends PatientAccount
{
    void labCharges(int cash)
    {
    	
    }
	@Override
	void billing() {
		System.out.println(" by cash ");
		
	}
	   
}

class PatientHistory extends PaymentAccount
{
	  void additonalCharges(int cash)
	  {
		  
	  }
	   
}



 

public class SampleLiskov {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
