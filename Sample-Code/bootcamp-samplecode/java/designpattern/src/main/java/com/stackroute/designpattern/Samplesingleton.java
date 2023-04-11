package com.stackroute.designpattern;


class Policy
{
	static String policyDetails;
	
	public static String getPolicyDetails() {
		return policyDetails;
	}

	public  static void setPolicyDetails(String policyDetails) {
		policyDetails = policyDetails;
	}

	private Policy()
	{
		
	}
	
	static Policy policyobj=new Policy();
	
	public static Policy getPolicy(String detail)
	{
	 
		if(detail.equals("personal"))
			policyDetails="Healthcheckup, Leave, Claim";
		else 
		policyDetails="Investment, Recruitment";
		return policyobj;
		
	}
}


public class Samplesingleton {

	public static void main(String[] args) {


		 Policy policyobj=Policy.getPolicy("personal");
		 
		 System.out.println(policyobj.getPolicyDetails());
		 
		 Policy policyobj1=Policy.getPolicy("accounts");
		 
		 System.out.println(policyobj1.getPolicyDetails());
		 
		
		
	}

}
