package com.stackroute.java8sess2.streamcasestudy;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ShoppingProcess {

	static List<Shopping> shoppers=ShoppingRepo.getCustomer();
	public static void main(String[] args) {
	
	
		
		//1. find the count of customers in the given city
	//	getCustomerByCityname("newyork");
	   
	//	2. find the list of customer names who ordered specific product in cityname descending order
		// getCustomerCityorder("Grocery");		

		
		//3. find the customer whose delivery got delayed 
		// getDeliveryDelayedReport();
		
		//4. return linked list of customers in the given city by sorting using customer name and then by product
		//getLinkedNameSort("canada");
		
		
		//5. return map of customer name and their city
		//getMapOfNameAndCity();
		
		
		
		//6. List the prepaid customer name , separated by ","
		//getPrePaidCustomer();
		
		
		//7. return the optional list of customer name belongs to a city and not paid
		Optional<List> optlist=getOptionalCustomerNotPaid("newyork");

		if(optlist.isPresent())
	
			System.out.println(optlist.get());
		
	}

	static void getCustomerByCityname(String city)
	{
		
	long ans=	shoppers.stream().filter( shop->shop.getCity().getCityName().equals(city)).count();
	System.out.println("Customers from " + city+ " " + ans);
		
	}
	
	
	static void getCustomerCityorder(String product)
	{
		Function<Shopping,String> fun=(shop)->shop.getCity().getCityName();
		
	List<String> names=	shoppers.stream().filter( shop-> shop.getProduct().equals(product))
						.sorted(Comparator.comparing(fun).reversed())
						.map(shop->shop.getCustomerName())
						.collect(Collectors.toList());
	System.out.println(names);
		
	}
	
	static void getDeliveryDelayedReport()
	{
		final int maxdays;
		
		OptionalInt optmax= shoppers.stream().mapToInt( shop->shop.getDeliveryPeriod()).max();
		if (optmax.isPresent())
			maxdays=optmax.getAsInt();
		else
			maxdays=0;
		
		List<Shopping> delayedlist= shoppers.stream().filter( shop->shop.getDeliveryPeriod()==maxdays).collect(Collectors.toList());
	//	delayedlist.forEach(System.out::println);
		
		
		//alternate
		
//	Optional<Shopping> optshopobj=shoppers.stream().max(Comparator.comparing(Shopping::getDeliveryPeriod));
//	
//	if(optshopobj.isPresent())
//		System.out.println(optshopobj.get());
//	
		
	}
	
	static void getLinkedNameSort(String city)
	{
		
	LinkedList<Shopping> shoplist=	shoppers.stream().filter( shop->shop.getCity().getCityName().equals(city))
		                  							.sorted(Comparator.comparing(Shopping::getCustomerName).thenComparing(Shopping::getProduct))
		                  							.collect(Collectors.toCollection(LinkedList::new));
	System.out.println(shoplist);
	}
	
	
	
	
	static void getMapOfNameAndCity()
	{
		Function<Shopping,String> fun1=(shop)->shop.getCustomerName();
		Function<Shopping,String> fun2=(shop)->shop.getCity().getCityName();
		
	Map<String,String> mapdata=shoppers.stream().collect(Collectors.toMap(fun1, fun2));
	
	System.out.println(mapdata);
	
	
//	Map<String,String> mapdata1= shoppers.stream().collect(Collectors.toMap( sobj->sobj.getCustomerName(), sobj->sobj.getCity().getCityName()));
	
	
	}
	
	
	static void getPrePaidCustomer()
	{
		
	String ans=	shoppers.stream().filter( shop->shop.getPaymentStatus().equals("Paid"))
						.map(shop->shop.getCustomerName())
					 .collect(Collectors.joining(","));
		System.out.println(ans);
		
	}
	
	
	static Optional<List> getOptionalCustomerNotPaid(String city)
	{
	List<String> names=	 shoppers.stream().filter( shop->shop.getCity().getCityName().equals(city) &&  shop.getPaymentStatus().equals("Not Paid"))
		 				 .map(shop->shop.getCustomerName())
		 				 .collect(Collectors.toList());
	
	return Optional.of(names);
	
	}
	
}
