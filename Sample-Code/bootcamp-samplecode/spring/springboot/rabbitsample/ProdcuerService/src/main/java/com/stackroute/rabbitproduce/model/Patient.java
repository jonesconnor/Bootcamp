package com.stackroute.rabbitproduce.model;

import org.springframework.stereotype.Component;

@Component
public class Patient {
	
	String patientid;
	String patientName;
	String problem;
	@Override
	public String toString() {
		return "Patient [patientid=" + patientid + ", patientName=" + patientName + ", problem=" + problem + "]";
	}
	public String getPatientid() {
		return patientid;
	}
	public void setPatientid(String patientid) {
		this.patientid = patientid;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getProblem() {
		return problem;
	}
	public void setProblem(String problem) {
		this.problem = problem;
	}
	

}
