package com.stackroute.oops;

/*
   This class is used to store account details
 */
public class Account {
	
	private int accountNo;
	private double balance;
	private String status;
	private static int count;
	
	public static void main(String[] args) {
		
		Account cheque1 = new Account(100, "active");
		String deets = cheque1.getAccountDetails();
		System.out.println(deets);
		
	}
	
	
    public Account(double balance, String status) {
    	this.balance = balance;
    	this.status = status;
    	count++;
    	accountNo = count;
    }

    public boolean depositAmount(double amount) {
    	if (getStatus().equals("active")) {
    		balance += amount;
    		return true;
    	}
        return false;
    }

    public String getAccountDetails() {
    	
    	String accountNum = "Account No : " + getAccountNo() + ", ";
    	
    	String stat = "Status : " + getStatus() + ", ";
    	
    	String bal = "Balance : " + getBalance();
    	
    	String details = "[" + accountNum + stat + bal + "]";
        
    	return details;
    }

    public static int getCounter() {
        return count;
    }

    public static void setCounter(int counter) {
    	count = counter;
    }

    public int getAccountNo() {
        return accountNo;
    }

    public double getBalance() {
        return balance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
    	this.status = status;
    }
}