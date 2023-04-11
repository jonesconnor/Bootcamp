package com.stackroute.streams;

public class Country {
	
	private String countryCode;
	private String countryName;
	
	public Country() {
	}
	
	public Country(String countryCode, String countryName) {
		setCountryCode(countryCode);
		setName(countryName);
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getName() {
		return countryName;
	}

	public void setName(String countryName) {
		this.countryName = countryName;
	}

}
