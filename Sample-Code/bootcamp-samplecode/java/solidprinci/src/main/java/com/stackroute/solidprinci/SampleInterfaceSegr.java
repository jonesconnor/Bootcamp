package com.stackroute.solidprinci;
interface iTemp
{
	 void calculatePerday();
}

interface iPerm
{
	 void overallSalary();
	  void appraisalProcess();
}
interface iPaymentProcess extends iTemp,iPerm
{
	
	
}

class PermEmployee implements iPerm
{


	public void overallSalary() {
	System.out.println("4500$");
		
	}

	public void appraisalProcess() {
		System.out.println(" in the month of May");
		
	}
	
}
class TempEmployee implements iTemp
{

	public void calculatePerday() {
			System.out.println("100$ per day");
		
	}


	
}


public class SampleInterfaceSegr {

	public static void main(String[] args) {
	 
		TempEmployee temp=new TempEmployee();
		temp.calculatePerday();

	}

}
