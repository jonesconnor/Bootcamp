package com.stackroute.java8sess3.datetime;

import java.time.LocalDate;

public class Employee {

	@Override
	public String toString() {
		return "Employee [empName=" + empName + ", dob=" + dob + ", doj=" + doj + "]";
	}
	String empName;
	LocalDate dob;
	LocalDate doj;
	public Employee(String empName, LocalDate dob, LocalDate doj) {
		super();
		this.empName = empName;
		this.dob = dob;
		this.doj = doj;
	}
	public LocalDate getDoj() {
		return doj;
	}
	public void setDoj(LocalDate doj) {
		this.doj = doj;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	
	
}
