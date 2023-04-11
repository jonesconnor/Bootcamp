package com.stackroute.java8sess2.streamcasestudy;

class City
{
	String cityName;
	@Override
	public String toString() {
		return "City [cityName=" + cityName + ", population=" + population + "]";
	}
	int population;
	public City(String cityName, int population) {
		super();
		this.cityName = cityName;
		this.population = population;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public int getPopulation() {
		return population;
	}
	public void setPopulation(int population) {
		this.population = population;
	}
}


public class Shopping {
   String customerName;
   public String getCustomerName() {
	return customerName;
}
public void setCustomerName(String customerName) {
	this.customerName = customerName;
}
public String getProduct() {
	return product;
}
public void setProduct(String product) {
	this.product = product;
}
@Override
public String toString() {
	return "Shopping [customerName=" + customerName + ", product=" + product + ", deliveryPeriod=" + deliveryPeriod
			+ ", paymentStatus=" + paymentStatus + ", city=" + city + "]";
}
public Shopping(String customerName, String product, int deliveryPeriod, String paymentStatus, City city) {
	super();
	this.customerName = customerName;
	this.product = product;
	this.deliveryPeriod = deliveryPeriod;
	this.paymentStatus = paymentStatus;
	this.city = city;
}
public int getDeliveryPeriod() {
	return deliveryPeriod;
}
public void setDeliveryPeriod(int deliveryPeriod) {
	this.deliveryPeriod = deliveryPeriod;
}
public String getPaymentStatus() {
	return paymentStatus;
}
public void setPaymentStatus(String paymentStatus) {
	this.paymentStatus = paymentStatus;
}
public City getCity() {
	return city;
}
public void setCity(City city) {
	this.city = city;
}
String product;
   int deliveryPeriod;
   String paymentStatus;
   City city;
   
	
}


