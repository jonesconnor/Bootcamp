package com.stackroute.firstapp;

public class Product {
	
	int productId;
	String productName;
	String company;
	public Product()
	{
		
	}
	
	public Product(int productId, String productName, String company) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.company = company;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	

}
