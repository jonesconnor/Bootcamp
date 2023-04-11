package com.stackroute.javadaythree;

public class ApplicantProcess {

	public static void main(String[] args) {
		
		
		iApplicant applicant; // loosely coupled
		
		applicant=AdmissionService.getAdmissionMaster("Wilson","23/Jan/23");
		
		System.out.println(applicant.viewStartDate());
		
		applicant.showAll();
		

	}

}
