package com.stackroute.java8sess3;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class CollectorsProcess {

	public static void main(String[] args) {
		
		List<Shopping> shoppers=ShoppingRepo.getCustomer();

		
		Map<String,List<Shopping>> mapshop=shoppers.stream().collect(Collectors.groupingBy(shop->shop.getCity().getCityName()));

//	mapshop.forEach( (k,v)-> System.out.println("key is " + k + " value is " + v ));
	
	
	Map<String,Long> citycount=shoppers.stream().collect(Collectors.groupingBy(shop->shop.getCity().getCityName(),
																						 Collectors.counting()
																						 )
																   );
   
	//citycount.forEach( (k,v)-> System.out.println("city name  is " + k + " count of shoppers " + v ));


	
	// paid customers ,average delivery period based on cityname
	
	
	Map<String,Double> 	mapavg=shoppers.stream().filter( shop->shop.getPaymentStatus().equals("Paid"))
						                 .collect(Collectors.groupingBy( shop->shop.getCity().getCityName(),
										             				 Collectors.averagingInt( Shopping::getDeliveryPeriod)
													              	)
								                  );
	
	
	//mapavg.forEach( (k,v)-> System.out.println("city name  is " + k + "  Average deliveryperiod " + v ));
	
	// max delivery period , based on payment status
	
	
	Map<String,Optional<Shopping>> mapmaxdelivery=		shoppers.stream().collect(Collectors.groupingBy( shop->shop.getPaymentStatus(),
									
																		Collectors.maxBy(Comparator.comparing(Shopping ::getDeliveryPeriod))
															         	)
																);
	
	//mapmaxdelivery.forEach( (k,v)->System.out.println(" Payment status " + k + " Maximum Delayed delivery goes to " + v.get()));

	
	Map<String,List<String>> mapnamelist=shoppers.stream().collect(Collectors.groupingBy( shop->shop.getCity().getCityName(),
																	Collectors.mapping( shopobj->shopobj.getCustomerName(),Collectors.toList() )
																						)
																);
	
	//mapnamelist.forEach((k,v)-> System.out.println("City is " + k + " customers list " + v));
	
	
	Map<String,String> mapnamelist1=shoppers.stream().collect(Collectors.groupingBy( shop->shop.getCity().getCityName(),
																Collectors.mapping( shopobj->shopobj.getCustomerName(),Collectors.joining(",") )
																	) //grouping
															  ); //collect
	
	mapnamelist1.forEach((k,v)-> System.out.println("City is " + k + " customers are " + v));


	Map<String,Long> mapnamelist2=shoppers.stream().collect(Collectors.groupingBy( shop->shop.getCity().getCityName(),
													Collectors.mapping( shopobj->shopobj.getCustomerName(),Collectors.counting() )
														) //grouping
										); //collect
	
	
	
	LinkedHashMap link=							shoppers.stream().collect(Collectors.groupingBy( shop->shop.getCity().getCityName(),
																				 LinkedHashMap::new,
																				 Collectors.counting()
																					
															)//grouping
												);
	
	link.forEach( (k,v) -> System.out.println(k + "count is  " + v ));
	
	
	}

}
