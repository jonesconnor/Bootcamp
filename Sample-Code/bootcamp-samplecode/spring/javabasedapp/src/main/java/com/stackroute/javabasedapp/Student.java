package com.stackroute.javabasedapp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class Student {
   
	  String rollno;
	  String name;
	  
	  @Autowired
	  List<College> colleges;
	 
	  
	  public List<College> getColleges() {
		return colleges;
	}





	public void setColleges(List<College> colleges) {
		this.colleges = colleges;
	}





	public  Student(){}
	  
	  
	 
	  
	  
	public String getRollno() {
		return rollno;
	}
	public void setRollno(String rollno) {
		this.rollno = rollno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}





	@Override
	public String toString() {
		return "Student [rollno=" + rollno + ", name=" + name + ", colleges=" + colleges + "]";
	}
 
	
}
