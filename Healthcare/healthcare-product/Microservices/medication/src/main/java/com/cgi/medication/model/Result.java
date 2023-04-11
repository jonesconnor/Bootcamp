package com.cgi.medication.model;

import java.util.List;

public class Result {
	String id;
	List<String> inactive_ingredient;
	List<String> purpose;
	List<String> warnings;
	List<String> indications_and_usage;
	List<String> active_ingredient;
	List<String> dosage_and_administration;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<String> getActive_ingredient() {
		return active_ingredient;
	}
	public void setActive_ingredient(List<String> active_ingredient) {
		this.active_ingredient = active_ingredient;
	}
	public List<String> getDosage_and_administration() {
		return dosage_and_administration;
	}
	public void setDosage_and_administration(List<String> dosage_and_administration) {
		this.dosage_and_administration = dosage_and_administration;
	}
	public List<String> getInactive_ingredient() {
		return inactive_ingredient;
	}
	public void setInactive_ingredient(List<String> inactive_ingredient) {
		this.inactive_ingredient = inactive_ingredient;
	}
	public List<String> getPurpose() {
		return purpose;
	}
	public void setPurpose(List<String> purpose) {
		this.purpose = purpose;
	}
	public List<String> getWarnings() {
		return warnings;
	}
	public void setWarnings(List<String> warnings) {
		this.warnings = warnings;
	}
	public List<String> getIndications_and_usage() {
		return indications_and_usage;
	}
	public void setIndications_and_usage(List<String> indications_and_usage) {
		this.indications_and_usage = indications_and_usage;
	}
	
	@Override
	public String toString() {
		return ("Id: "+this.getId()+" Inactive Ingredient: "+this.getInactive_ingredient()+" Purpose: "+this.getPurpose()+" Warnings: "+this.getWarnings()+" Indications and Usage: "+this.getIndications_and_usage()+" Active Ingredient: "+this.getActive_ingredient()+" Dosage and Administration: "+this.getDosage_and_administration());
	}
}
