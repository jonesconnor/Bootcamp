package com.stackroute.javadaytwo;

enum Transact
{
	   ADMIN,
	   EMPLOYEE,
	   REPO,
	   SERVICE
	
}

public class ResourceManagement {

	public static void main(String[] args) {

	//	System.out.println(Admin.num);
		
		EmployeeRepo repository=new EmployeeRepo();
		repository.initEmployee();
		Employee[] employees=repository.getAllemployees();
		
		for(Employee emp : employees)
		{
			System.out.println(emp);
		}
		
		Transact transaction=Transact.ADMIN;
		
		System.out.println(transaction);
		
		switch(transaction)
		{
		case ADMIN:
					new ResourceManagement().adminProcess();
		}
		
		
		
	  //new ResourceManagement().employeeProcess();
	  //		
//		new ResourceManagement().recruitmentProcess("Tester");
//		
//		new ResourceManagement().recruitmentProcess("Developer");
//		
//		
//		new ResourceManagement().recruitmentProcess("UI designer");
//		
//		Recuritment.displayDetails();
//		
//		new ResourceManagement().finalSettlement();
//		
//		Recuritment.displayDetails();
		
	}
	
//	void recruitmentProcess(String role)
//	{
//		
//		Recuritment recruitobj=new Recuritment();
//		recruitobj.setRole(role);
//		System.out.println ("Role is " + recruitobj.getRole() + "overall employee  count is " + recruitobj.newRecruitment());
//		
//		
//	}
//	
//	void finalSettlement()
//	{
//		Recuritment recruitobj=new Recuritment();
//		recruitobj.termination();
//
//		
//		
//	}
	
	
	void adminProcess()
	{
		
		Admin admin=new Admin();
		admin.setAdminName("Kevin");
		admin.setExperience(10);
		admin.setPolicy(new StringBuilder("employee healthcare"));
		
		admin.addDataToPolicy(" System");
		
		System.out.println(admin);
	}

	
	void employeeProcess()
	{
		 
//			Employee emp;  // object created
//			emp=new Employee(10,"Dan","BLore",100,60000); // instantiation -- allocate memory
//			
//			
//			emp.setEmpId(10);
//			emp.setEmpName("Tom");
//			emp.setAddr("Canada");
//			emp.setDeptid(10);
//			emp.setSalary(50000);
			
		 EmployeeService empservice=new EmployeeService();
		 
		 Employee emp=EmployeeService.createEmployee();
		 
			emp.setEmpId(20);
			emp.setEmpName("Vincent");
			emp.setAddr("Canada");
			emp.setDeptid(10);
			emp.setSalary(50000);
		 
		  System.out.println (" Your salary is " + emp.getSalary() + " Tax is " + empservice.calculateTax(emp));
		  

		  
		  empservice.assignTraining(emp);
		  
		 System.out.println( empservice.validateName(emp.getEmpName())) ;

		  
		  System.out.println("Current Address" + emp.getAddr());
	}
}
