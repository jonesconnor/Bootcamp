package com.stackroute.designpattern;

interface iClaim
{
	 void viewSettlement();
}

abstract class AbstractFactory
{
	abstract iClaim getProcess(String type);
}

class HealthCheckup implements iClaim
{
	public void viewSettlement() {
	System.out.println("Cashless : Master health check only 10$ allowed");
	}
}

class PartnerHospital implements iClaim
{
	public void viewSettlement() {
	System.out.println("Cashless is possible only in preferred hospital treatments");
	}
}


class InsuranceDaycare implements iClaim
{

	public void viewSettlement() {
		System.out.println("Reimbursement : Allowed for Catract,Sinus problems with proper document");
		
	}
	
}

class NetworkHosptial implements iClaim
{

	public void viewSettlement() {
		System.out.println("Reimbursement : Allowed for hospitals with 40beds and 10ICU care");
		
	}
	
}

class Reimburse extends AbstractFactory
{

	@Override
	iClaim getProcess(String type) {
		 if (type.equalsIgnoreCase("daycare"))
			 
		return new InsuranceDaycare();
		 else
			 return new NetworkHosptial();
	}
	
	
}


class Cashless extends AbstractFactory
{
	@Override
	iClaim getProcess(String type) {
		   if(type.equalsIgnoreCase("health"))
			   return new HealthCheckup();
		   else
		   return new PartnerHospital();
		   
		
		 
	}
}	
	
class FactoryProducer
{
	   public static AbstractFactory getFactory(String processtype)
	   {
		     if (processtype.equals("cashless"))
		    	 return new Cashless();
		     else
		    	 return new Reimburse();
	   }
	
}
	





public class SampleAbstractFactory {

	public static void main(String[] args) {
		AbstractFactory absfactory=FactoryProducer.getFactory("reimburse");
	iClaim claimobj=	absfactory.getProcess("daycare");
	claimobj.viewSettlement();
	
	
	}

}
