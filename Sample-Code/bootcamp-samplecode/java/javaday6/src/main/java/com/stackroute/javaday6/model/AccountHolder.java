package com.stackroute.javaday6.model;

public class AccountHolder {

	@Override
	public String toString() {
		return "AccountHolder [accId=" + accId + ", name=" + name + ", balance=" + balance + "]";
	}
	public AccountHolder(int accId, String name, int balance) {
		super();
		this.accId = accId;
		this.name = name;
		this.balance = balance;
	}
	int accId;
	public void setAccId(int accId) {
		this.accId = accId;
	}
	public int getAccId()
	{
		return accId;
	}
	String name;
	int balance;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	public int hashCode()
	{
		return accId;
	}
	
	
	public boolean equals(Object obj)
	{
		AccountHolder acc2=(AccountHolder) obj;
		
		return acc2.getAccId()==this.getAccId();
	}
}
