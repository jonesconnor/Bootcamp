package com.cgi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Driver {
	
	@Id
	private int driverid;
	private String firstname;
	private long telephonenumber;
	private String address;
	private String city;
	private String enginesize;
	private long quoteamount;
	
	public Driver() {}
	
	public Driver(int driverid, String firstname, long telephonenumber, String address, String city, String enginesize,
			long quoteamount) {
		this.driverid = driverid;
		this.firstname = firstname;
		this.telephonenumber = telephonenumber;
		this.address = address;
		this.city = city;
		this.enginesize = enginesize;
		this.quoteamount = quoteamount;
	}
	
	public int getDriverid() {
		return driverid;
	}
	public void setDriverid(int driverid) {
		this.driverid = driverid;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public long getTelephonenumber() {
		return telephonenumber;
	}
	public void setTelephonenumber(long telephonenumber) {
		this.telephonenumber = telephonenumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getEnginesize() {
		return enginesize;
	}
	public void setEnginesize(String enginesize) {
		this.enginesize = enginesize;
	}
	public long getQuoteamount() {
		return quoteamount;
	}
	public void setQuoteamount(long quoteamount) {
		this.quoteamount = quoteamount;
	}
	
}
