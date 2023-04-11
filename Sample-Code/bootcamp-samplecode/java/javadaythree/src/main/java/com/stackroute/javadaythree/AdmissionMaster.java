package com.stackroute.javadaythree;

interface iApplicant
{
	 String viewStartDate();
	    void showAll();
}


interface iAdmin
{
	 String viewStartDate();
	    void showAll();
	    void setFees();
	     void setStatus(int score);
}

interface iAdminV2 extends iAdmin
{
	 void setConstructions();
	
}

public class AdmissionMaster implements iAdmin,iApplicant,iAdminV2{
	
    String applicantName;
    
    String status;
    
    int fees;
    
    String startDate;
    
    int score;
    
    AdmissionMaster()
    {
    	
    }
    
    AdmissionMaster(String name,String date)
    {
    	this.applicantName=name;
    	this.startDate=date;
    }
    
   public void setConstructions()
   {
	   
   }
    
    public void setFees()
    {
    	 fees=45000;
    }
    
     public String viewStartDate()
     {
    	 return " Opens on " + startDate;
     }

     
     public void setStatus(int score)
     {
    	  
    	   if (score>80)
    		   status="Selected";
    	   else
    		   status="Rejected";
     }
     
     
     public void showAll()
     {
    	 System.out.println(" name " + applicantName + " status is " + status + " start Date " + startDate  ) ;
     }
     
    
    
	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getFees() {
		return fees;
	}

	public void setFees(int fees) {
		this.fees = fees;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
    
    
	

}
