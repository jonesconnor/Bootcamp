package com.stackroute.javadaythree;

public class Student extends Person{

        private String department;
        private String grade;
        
   	 
         private Address address;
   	 
	    public String getDepartment() {
			return department;
		}

		public void setDepartment(String department) {
			this.department = department;
		}

		public String getGrade() {
			return grade;
		}

		public void setGrade(String grade) {
			this.grade = grade;
		}

		public Address getAddress() {
			return address;
		}

		public void setAddress(Address address) {
			this.address = address;
		}

		Student()
	    {
	     System.out.println("Inside Student constructor");
	    	
	    }
        
	    Student(String name,String addr,int age,String dname,String grade)
	    {
	    	super(name,age,addr);
		     System.out.println("Inside Student Parameterized constructor");
		     	
    	this.department=dname;
	    	this.grade=grade;
//	    	this.setName(name);
		     
//	    	this.setAddr(addr);
//	    	this.setAge(age);
	    }
	    
	   public  void display()	//overrides
	    {
		   
		   
		   System.out.println("Exam calender - released ");
		   
//		   System.out.println( "Person [name=" + getName() + ", age=" + getAge() + ", addr=" + getAddr() + "]");
//	    	//System.out.println(display());
//	    	System.out.println("student details : Depart " + department + "Grade " + grade);
	    }
	   
	   public void getData()
	   {
		   System.out.println(" Books for reference : ABC author");
	   }
	   
	
}
