package com.cgi.model;

public class Plan {
	
	private String planid;
	private String planname;
	private long period;
	private long amount;
	
	public Plan() {}
	
	public Plan(String planid, String planname, long period, long amount) {
		this.planid = planid;
		this.planname = planname;
		this.period = period;
		this.amount = amount;
	}

	public String getPlanid() {
		return planid;
	}

	public void setPlanid(String planid) {
		this.planid = planid;
	}

	public String getPlanname() {
		return planname;
	}

	public void setPlanname(String planname) {
		this.planname = planname;
	}

	public long getPeriod() {
		return period;
	}

	public void setPeriod(long period) {
		this.period = period;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}
	
	

}
