package com.stackroute.javaday6.repository;

import java.util.ArrayList;
import java.util.List;

import com.stackroute.javaday6.model.AccountHolder;

public class AccountRepo {

	static List<AccountHolder> accountholders=new ArrayList<AccountHolder>();
	
	static
	{
		
		//accountholders.add(5);
		
		accountholders.add(new AccountHolder(10,"Anto",5000));
		
		accountholders.add(new AccountHolder(20,"Annie",7000));
		
		
		accountholders.add(new AccountHolder(30,"Dan",9000));
		
		accountholders.add(new AccountHolder(40,"Mary",6000));
		
	}
	
	public static List getData()
	{
		return accountholders;
	}
	
	
	
	
	
}
