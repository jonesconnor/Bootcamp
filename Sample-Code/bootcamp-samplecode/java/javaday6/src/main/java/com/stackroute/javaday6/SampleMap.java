package com.stackroute.javaday6;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SampleMap {

	public static void main(String[] args) {
		Map<String,Integer> pricemap=new HashMap<String,Integer>();

		pricemap.put("laptop", 45000);
		pricemap.put("keyboard",3200);
		pricemap.put("wifi", 4100);
		pricemap.put("keyboard",600);
		
		System.out.println(pricemap);
		
		Set<String> keys=pricemap.keySet();
		
		for(String key : keys)
		{
			System.out.println(pricemap.get(key));
		}
		

		
		
		
	}

}
