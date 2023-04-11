package com.stackroute.solidprinci;

class Medicine implements iMedicineProcess
{
	String drugName;
	int price;
	int qty;
	 public void stockDetails()
	 {
		 System.out.println( " stock " + drugName  + " avaialbe qty " + qty );
	 }
	public void addMedicine(int qty) {
		// TODO Auto-generated method stub
		
	}
	public void sellQty(int qty) {
		// TODO Auto-generated method stub
		
	}
	
}

 interface iMedicineProcess
 {
	   void addMedicine(int qty);
	   void stockDetails();
	   void sellQty(int qty);
 }

class PharmaIncharge
{
	iMedicineProcess medicine;
	
		PharmaIncharge(Medicine med)
		{
			this.medicine=med;
		}
	
		 void process()
		 {
			 medicine.addMedicine(20);
		 }
		
		
}

class SalesPerson
{
	
	iMedicineProcess medicine;
	SalesPerson(Medicine med)
	{
		this.medicine=med;
	}

	void process()
	{
		medicine.sellQty(10);
	}
	
}  


public class SampleDI {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
