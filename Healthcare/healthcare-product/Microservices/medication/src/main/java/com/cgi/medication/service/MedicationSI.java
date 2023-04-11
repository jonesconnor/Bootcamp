package com.cgi.medication.service;



import com.cgi.medication.exception.ViewAllFailedException;
import com.cgi.medication.model.Medication;

public interface MedicationSI {
	
	 Medication getAllMedications() throws ViewAllFailedException;
	
	

}
