package com.stackroute.javadaythree;

public class AdmissionService {

	
	public static AdmissionMaster getAdmissionMaster(String name,String dt)
	{
		return new AdmissionMaster(name,dt);
	}
	
}
