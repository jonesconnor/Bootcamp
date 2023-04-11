package com.stackroute.javaday6.service;

import java.util.List;

import com.stackroute.javaday6.model.AccountHolder;

public interface iService {
	
	public void addAccountHolder(AccountHolder accholder);
	public List getAccountHolders();
	public void deleteHolder(int accid);
	
}
