package com.cgi.booking.model;

import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Booking {
	
	@Id
	String id;
	
	String patientId;
	boolean isEmergency;
	String serviceProviderId;
	String subject;
	String paymentToken;
	TimeSlot timeSlot;
	String location; 	 //zoom or online
	int status; 		 //(0, 1, 2, 3) – pending, confirmed, updated, complete
	String recurrenceType;		 //(daily/weekly)
	int recurrenceInterval;
	LocalDate recurrenceEndDate;
	
	public Booking() {
	}
	
	

	public Booking(String id, String patientId, boolean isEmergency, String serviceProviderId, String subject,
			String paymentToken, TimeSlot timeSlot, String location, int status, String recurrenceType,
			int recurrenceInterval, LocalDate recurrenceEndDate) {
		this.id = id;
		this.patientId = patientId;
		this.isEmergency = isEmergency;
		this.serviceProviderId = serviceProviderId;
		this.subject = subject;
		this.paymentToken = paymentToken;
		this.timeSlot = timeSlot;
		this.location = location;
		this.status = status;
		this.recurrenceType = recurrenceType;
		this.recurrenceInterval = recurrenceInterval;
		this.recurrenceEndDate = recurrenceEndDate;
	}



	@Override
	public String toString() {
		return "Booking [id=" + id + ", patientId=" + patientId + ", isEmergency=" + isEmergency
				+ ", serviceProviderId=" + serviceProviderId + ", subject=" + subject + ", paymentToken=" + paymentToken
				+ ", timeSlot=" + timeSlot + ", location=" + location + ", status=" + status + ", recurrenceType="
				+ recurrenceType + ", recurrenceInterval=" + recurrenceInterval + ", recurrenceEndDate="
				+ recurrenceEndDate + "]";
	}



	public String  getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public boolean isEmergency() {
		return isEmergency;
	}

	public void setEmergency(boolean isEmergency) {
		this.isEmergency = isEmergency;
	}

	public String getServiceProviderId() {
		return serviceProviderId;
	}

	public void setServiceProviderId(String serviceProviderId) {
		this.serviceProviderId = serviceProviderId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getPaymentToken() {
		return paymentToken;
	}

	public void setPaymentToken(String paymentToken) {
		this.paymentToken = paymentToken;
	}

	public TimeSlot getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(TimeSlot timeSlot) {
		this.timeSlot = timeSlot;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getRecurrenceType() {
		return recurrenceType;
	}

	public void setRecurrenceType(String recurrenceType) {
		this.recurrenceType = recurrenceType;
	}

	public int getRecurrenceInterval() {
		return recurrenceInterval;
	}

	public void setRecurrenceInterval(int recurrenceInterval) {
		this.recurrenceInterval = recurrenceInterval;
	}

	public LocalDate getRecurrenceEndDate() {
		return recurrenceEndDate;
	}

	public void setRecurrenceEndDate(LocalDate recurrenceEndDate) {
		this.recurrenceEndDate = recurrenceEndDate;
	}


	
}
