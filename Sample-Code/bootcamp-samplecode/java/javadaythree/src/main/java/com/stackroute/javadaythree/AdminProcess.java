package com.stackroute.javadaythree;

public class AdminProcess {

	public static void main(String[] args) {
		
//		AdmissionMaster adminmaster; // tightly 
//		adminmaster=new AdmissionMaster("Ross","12/Jan/2023");
//		
		
		iAdmin admin; // loosely coupled object
		admin=AdmissionService.getAdmissionMaster("Ross", "12/Jan/2023");
		
		
		//admin=new AdmissionMaster("Ross","12/Jan/2023"); 
		
		
		
		admin.setFees();
		admin.setStatus(45);
		admin.showAll();
		
		iAdminV2 admin2=AdmissionService.getAdmissionMaster("Veronica", "10/01/2023");
		
		admin2.viewStartDate();
		
		
	}

}
