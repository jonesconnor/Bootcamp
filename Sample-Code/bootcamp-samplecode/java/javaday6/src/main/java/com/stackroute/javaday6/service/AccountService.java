package com.stackroute.javaday6.service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import com.stackroute.javaday6.model.AccountHolder;
import com.stackroute.javaday6.repository.AccountRepo;

public class AccountService implements iService {

	List accountholders=new ArrayList();
	
	AccountService()
	{
	   accountholders=AccountRepo.getData();	
	}
	
	public void addAccountHolder(AccountHolder accholder)
	{
		accountholders.add(accholder);
	}
	
	public List getAccountHolders()
	{
		return accountholders;
	}
	
	public void deleteHolder(int accid)
	{
		
		ListIterator<AccountHolder> acciterator=accountholders.listIterator();
		
		while(acciterator.hasNext())
		{
						 
					 				
			AccountHolder accobj=acciterator.next();
			 if(accobj.getAccId()==accid)
			 {
				 acciterator.remove();
			 }
		}
	}
	
	public static AccountService getAccountService()
	{
		return new AccountService();
	}
	
	
}
