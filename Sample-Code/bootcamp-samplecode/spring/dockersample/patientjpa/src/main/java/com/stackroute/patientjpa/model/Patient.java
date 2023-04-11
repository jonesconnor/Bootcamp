package com.stackroute.patientjpa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Patient {

	@Id
	int patientId;
	
	String patientName;
	String addr;
	String problem;
 
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getProblem() {
		return problem;
	}
	public void setProblem(String problem) {
		this.problem = problem;
	}
	
	
	
}
