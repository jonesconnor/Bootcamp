package com.stackroute.javadaythree;

public abstract class Person {

	private  String name;
	 private int age;

	 
	 Address address;
	 
	  
	  public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Person()
	  {
		     System.out.println("Inside Parent Default constructor");

	  }

		public Person(String name, int age, String addr) {
			super();
		     System.out.println("Inside Parent Parameterized constructor");

			this.name = name;
			this.age = age;
	//		this.addr = addr;
		}
	  
		
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	public abstract void display() ;
	
	 public abstract void getData();
	 
	
}
