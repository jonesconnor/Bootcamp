package com.stackroute.java8sess1.fis;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class SampleConsumer {

	public static void main(String[] args) {

		List<Integer> resultodd=new ArrayList();
		
	Consumer<Integer> consumer= (num)-> {
											if(num%2!=0)
												//resultodd.add(num);
												System.out.println(num);
									};
	
	List<Integer> data=Arrays.asList(12,14,23,45,68,79);
	
	for(int num:data)
	{
		consumer.accept(num);
	}
	
	System.out.println(resultodd);

	
	Supplier<String> supplytoday= ()->  LocalDate.now().toString();
	System.out.println(supplytoday.get());
	
	}

}



