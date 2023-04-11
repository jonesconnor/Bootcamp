package com.stackroute.javadaytwo;

final public class Admin {
	
	private String adminName;
	private int experience;
	private StringBuilder policy;
	
	
   public static final int num=15;
   
	@Override
	public String toString() {
		return "Admin [adminName=" + adminName + ", experience=" + experience + ", policy=" + policy + "]";
	}
	public StringBuilder getPolicy() {
		return policy;
	}
	public void setPolicy(StringBuilder policy) {
		this.policy = policy;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
	
	void addDataToPolicy(String data)
	{
		final String duration;
		duration="2023 to 2024";
		
	
		policy.append(data);
	}
	

}
