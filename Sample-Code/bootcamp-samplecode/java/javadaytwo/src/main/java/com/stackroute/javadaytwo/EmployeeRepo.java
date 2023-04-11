package com.stackroute.javadaytwo;

import java.util.Scanner;



public class EmployeeRepo {
	
	static Employee[] employees= new Employee[3]; // object creation
	Scanner scanobj=new Scanner(System.in);
	
	void initEmployee()
	{
		for (int i=0;i<=2;i++)
		{
			employees[i]=new Employee(); // instantiation 
		}
		
		for (int i=0;i<=2;i++)
		{
			employees[i].setEmpId(i+1);
			System.out.println("Enter name");
			employees[i].setEmpName(scanobj.next());
			
		}
		
	}
	
	static Employee[] getAllemployees()
	{
		return employees;
	}
	
}
