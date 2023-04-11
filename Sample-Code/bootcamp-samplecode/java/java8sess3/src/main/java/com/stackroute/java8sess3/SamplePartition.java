package com.stackroute.java8sess3;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SamplePartition {

	public static void main(String[] args) {
	 
		List<Shopping> shoppers=ShoppingRepo.getCustomer();
		
		
	Map<Boolean,Long> partdata=	shoppers.stream().collect(Collectors.partitioningBy(shop->shop.getPaymentStatus().equals("Paid"),
													Collectors.counting()
															)
								 );
	
	partdata.forEach( (k,v)->System.out.println(" paid " + k + " count is " + v ));
	}

}
