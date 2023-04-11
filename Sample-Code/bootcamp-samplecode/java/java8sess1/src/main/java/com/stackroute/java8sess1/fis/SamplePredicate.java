package com.stackroute.java8sess1.fis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class SamplePredicate {

	public static void main(String[] args) {

		List<String> colors=Arrays.asList("red","rose","blue","black","white");
		
		Predicate<String> precolor= (col)->col.startsWith("r");
		
		
		for(String str : colors)
		{
			if (precolor.test(str))
					System.out.println(str);
		}
	
		
		ArrayList<String> items=new ArrayList();
		items.add("Juice");
		items.add("Cake");
		items.add("Biscuits");
		
		Predicate<String> preitem=(itm) -> itm.startsWith("J");
		
		items.removeIf(preitem);
		
		//items.remo
		
    items.forEach( (itm)->System.out.println(itm));
	
    System.out.println(items);
	
	}

}
