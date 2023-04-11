package com.cgi.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Insurance {
	
	@Id
	private String insuranceid;
	private String type;
	private List<Plan> plans;
	
	public Insurance () {}
	
	public Insurance(String insuranceid, String type, List<Plan> plans) {
		this.insuranceid = insuranceid;
		this.type = type;
		this.plans = plans;
	}
	public String getInsuranceid() {
		return insuranceid;
	}
	public void setInsuranceid(String insuranceid) {
		this.insuranceid = insuranceid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<Plan> getPlans() {
		return plans;
	}
	public void setPlans(List<Plan> plans) {
		this.plans = plans;
	}
	
	
}
