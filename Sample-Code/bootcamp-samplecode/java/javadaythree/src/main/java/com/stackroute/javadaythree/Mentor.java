package com.stackroute.javadaythree;

public class Mentor extends Person{

	 String department;
	 int experience;
	 
	 Mentor()
	 {
		 
	 }
	 
	public Mentor(String dname,int expr)
	 {
		 super("Annie",21,"Canada");
		 this.department=dname;
		 this.experience=expr;
	 }
	 
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
	 
	
	public void display()
	{
		   System.out.println( "Person [name=" + getName() + ", age=" + getAge() + ", addr="  + "]");
		System.out.println("mentor details:  Deparment " + department +  " experience " + experience);
	}
	
	public void getData()
	{
		 System.out.println(" Retrieve student file ");
		 
	}
}
