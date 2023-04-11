package com.stackroute.java8sess1.fis;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;

public class BiSamples {

	public static void main(String[] args) {

		BiPredicate<String,Integer> preobj=(nm,age)-> nm.length()>=3 && age >=18;
		
		 if ( preobj.test("Paul", 30) )
			 System.out.println("Eligible for Employement Visa");
		 else
			 System.out.println("Not Eligible ");

	BiConsumer<String,String> biconsume=(str1,str2)->
													{	
											System.out.println("Key " + str1 + " val is  " + str2 );
													};
													
													
      biconsume.accept("L01", "Laptop");
     
     Map<String , String> mydata=new HashMap();
     mydata.put("E1","english");
     mydata.put("S0","Spanish");
     mydata.put("Fk", "French");
     
     mydata.forEach(biconsume);
     
     
													
	}											
													
}
