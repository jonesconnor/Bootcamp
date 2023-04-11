package com.stackroute.javadaytwo;

public class Employee {
	
	public Employee()
	{
		System.out.println("Default constructor");
	}
	
	
	public Employee(int empId, String empName, String addr, int deptid, int salary) {
		//super();
		this.empId = empId;
		this.empName = empName;
		this.addr = addr;
		this.deptid = deptid;
		this.salary = salary;
	}

	
	public Employee(int empId, String empName, String addr ) {
		//super();
		this.empId = empId;
		this.empName = empName;
		this.addr = addr;
		 
	}
	

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", addr=" + addr + ", deptid=" + deptid + "]";
	}
	
	
	
	private int empId;
	private String empName;
	private String addr;
	private int deptid;
	private int salary;
	
	
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		if(empId>0)
		this.empId = empId;
		else
			
			this.empId=0;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public int getDeptid() {
		return deptid;
	}
	public void setDeptid(int deptid) {
		this.deptid = deptid;
	}
	
	
	
	

}
