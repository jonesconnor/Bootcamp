package com.stackroute.javaday6;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

import com.stackroute.javaday6.model.AccountHolder;

public class SampleSet {

	public static void main(String[] args) {
  
 		  Set<String> workdays=new LinkedHashSet();
 		  
 		  workdays.add("Monday");
 		  workdays.add("Tuesday");
 		  workdays.add("Wednesday");
		 // workdays.add("Tuesday");
		  
		  
 	//	  System.out.println(workdays);
	
 		  

 		  Set<String> workingdays=new TreeSet();
 		  
 		 workingdays.add("Monday");
 		workingdays.add("Wednesday");
 		workingdays.add("Tuesday");
 		workingdays.add("Friday");
		 // workdays.add("Tuesday");
 		

		//  System.out.println(workingdays);
		  
		  
		Set<AccountHolder> accountholders=new LinkedHashSet();

		
		
		
		accountholders.add(new AccountHolder(10,"Jemy",5000));
		accountholders.add(new AccountHolder(20,"Henry",5000));
		accountholders.add(new AccountHolder(30,"Farhas",6000));
		
		
		System.out.println(accountholders);
		
		
		
 		  
	}

}
