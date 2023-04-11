package com.stackroute.javaday6;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.stackroute.javaday6.model.AccountHolder;

public class MapAccountholder {

	public static void main(String[] args) {
		 
		Map<AccountHolder,String> accountholders=new HashMap<AccountHolder,String>();
		
		accountholders.put(new AccountHolder(10,"Jemy",5000),"Canada");
		accountholders.put(new AccountHolder(20,"Henry",5000),"Victoria");
		accountholders.put(new AccountHolder(30,"Farhas",6000),"Canada");
		
//		for(Map.Entry<AccountHolder, String > entryobj : accountholders.entrySet())
//		{
//			
//			AccountHolder accholder=entryobj.getKey();
//			System.out.println(accholder);
//			
//		}
			
			
		for(Map.Entry<AccountHolder, String > entryobj : accountholders.entrySet())
		{
			if (entryobj.getValue().contentEquals("Victoria"))
			{
			AccountHolder accholder=entryobj.getKey();
			
			System.out.println(accholder);

			}
		}
		

	}

}
