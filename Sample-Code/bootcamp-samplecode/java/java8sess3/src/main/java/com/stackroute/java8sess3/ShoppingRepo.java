package com.stackroute.java8sess3;

import java.util.ArrayList;
import java.util.List;

public class ShoppingRepo {

	
	static List<Shopping> customers=new ArrayList();
	
static
{
	customers.add(new Shopping("Mortein","Grocery",5,"Paid",new City("newyork",30400)));
	customers.add(new Shopping("Anto","Electronics",20,"Paid",new City("newyork",30400)));
	customers.add(new Shopping("Victor","Grocery",12,"Not Paid",new City("blore",33400)));
	customers.add(new Shopping("Rose","Furniture",30,"Paid",new City("canada",61400)));
	customers.add(new Shopping("Angel","Electronics",5,"Paid",new City("blore",33400)));
	customers.add(new Shopping("Tiger","Grocery",10,"Not Paid",new City("newyork",61400)));
	
}
	
	
	public static List<Shopping> getCustomer()
	{
		return customers;
		
		
	}
}
