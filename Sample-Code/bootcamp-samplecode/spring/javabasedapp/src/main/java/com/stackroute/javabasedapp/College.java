package com.stackroute.javabasedapp;

public class College {

	  String collegeName;
	  @Override
	public String toString() {
		return "College [collegeName=" + collegeName + ", addr=" + addr + "]";
	}
	String addr;
	  
	  public College(String collegeName, String addr) {
		super();
		this.collegeName = collegeName;
		this.addr = addr;
	}
	  
	  public void afterBeanCall()
	  {
		   System.out.println("College Bean initialized");
		  collegeName="Alaska College";
	  }
	  
	public College()
	  {
		  
	  }
	public String getCollegeName() {
		return collegeName;
	}
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	  
	
}
