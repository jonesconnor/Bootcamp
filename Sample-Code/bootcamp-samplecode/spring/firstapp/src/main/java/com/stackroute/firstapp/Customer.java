package com.stackroute.firstapp;

import org.springframework.beans.factory.annotation.Autowired;

public class Customer {

	String customerName;
	String mobile;
	Product product;
	
//	product=new Product();
	
	public String getCustomerName() {
		return customerName;
	}
	
	@Autowired
	public Customer(Product product) {
//		super();
//		this.customerName = customerName;
 	//	this.mobile = mbl;
		this.product = product;
	}
	
	public Customer()
	{
		
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
}
