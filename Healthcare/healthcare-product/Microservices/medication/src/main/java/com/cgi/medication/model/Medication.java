package com.cgi.medication.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document 
public class Medication {
	
	@Id
	String medid = "M1";
	List<Result> results;
	
	public String getMedid() {
		return medid;
	}

	public void setMedid(String medid) {
		this.medid = medid;
	}

	

public List<Result> getResults() {
	return results;
}

public void setResults(List<Result> results) {
	this.results = results;
}

@Override
public String toString() {
	return ("Medication Id: "+this.getMedid()+" Results: "+this.getResults());
}

}
