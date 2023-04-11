package com.stackroute.firstapp;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

public class Billing {

	
	 String billnumber;
	 String billdate;
	 Map feedback=new HashMap();
	 
	 public Map getFeedback() {
		return feedback;
	}
	public void setFeedback(Map feedback) {
		this.feedback = feedback;
	}
	@Autowired
	 Product productbean;
	 
	 
	public Product getProductbean() {
		return productbean;
	}
	public void setProductbean(Product productbean) {
		this.productbean = productbean;
	}
	public String getBillnumber() {
		return billnumber;
	}
	public void setBillnumber(String billnumber) {
		this.billnumber = billnumber;
	}
	public String getBilldate() {
		return billdate;
	}
	public void setBilldate(String billdate) {
		this.billdate = billdate;
	}
 
	 
	 
}
