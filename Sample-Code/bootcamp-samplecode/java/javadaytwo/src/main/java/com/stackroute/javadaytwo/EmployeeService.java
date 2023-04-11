package com.stackroute.javadaytwo;

public class EmployeeService {
	
	Employee employee;  // 
	
	float calculateTax(Employee emp)
	{
	    return emp.getSalary()*18/100;
	 	
	}
	
	void assignTraining(Employee employee)
	{
		System.out.println("congratulations " + employee.getEmpName() + " you have been nominated for JAva training" );
		employee.setAddr("US");
	}

	boolean validateName(String name)
	{
		name="Ask";
		return name.length()>=3;
	}
	
	public static Employee createEmployee()
	{
		
		return new Employee();
	}
	
}
