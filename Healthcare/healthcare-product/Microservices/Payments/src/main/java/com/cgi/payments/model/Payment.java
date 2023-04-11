package com.cgi.payments.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Payment {
	
	@Id
	String receiptId;
	
	String paymentToken;
	String serviceType;
	float hours; //# of hours
	float pricePerHour; // price per hour
	int status; //
	
	//Foreign keys with other MCs
	String payerId;
	String payeeId;
	
	public String getReceiptId() {
		return receiptId;
	}
	
	public void setReceiptId(String receiptId) {
		this.receiptId = receiptId;
	}
	
	public String getPaymentToken() {
		return paymentToken;
	}
	
	public void setPaymentToken(String paymentToken) {
		this.paymentToken = paymentToken;
	}
	
	public String getServiceType() {
		return serviceType;
	}
	
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	
	public float getHours() {
		return hours;
	}

	public void setHours(float hours) {
		this.hours = hours;
	}

	public float getPricePerHour() {
		return pricePerHour;
	}

	public void setPricePerHour(float pricePerHour) {
		this.pricePerHour = pricePerHour;
	}

	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public String getPayerId() {
		return payerId;
	}
	
	public void setPayerId(String payerId) {
		this.payerId = payerId;
	}
	
	public String getPayeeId() {
		return payeeId;
	}
	
	public void setPayeeId(String payeeId) {
		this.payeeId = payeeId;
	}
	
	
}
