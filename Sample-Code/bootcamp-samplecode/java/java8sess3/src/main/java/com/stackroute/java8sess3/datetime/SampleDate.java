package com.stackroute.java8sess3.datetime;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class SampleDate {

	public static void main(String[] args) {

		LocalDate today=LocalDate.now();
		
		LocalDate myjoindate=LocalDate.of(2022, 9, 24);
		
		
	//	System.out.println(today.getMonth());
		
		LocalDate threewk=today.plusWeeks(2);
		
//		System.out.println(threewk);
//		System.out.println(myjoindate);
//		
		LocalDate modijoindate=myjoindate.withMonth(5);
		
		//System.out.println(modijoindate);
		
		LocalDate frithree=today.plusWeeks(3).with(DayOfWeek.MONDAY);
		
		
		System.out.println("afer 3 weeks friday falls on " + frithree);

		if (myjoindate.isBefore(today))
			System.out.println(" employee already joined ");
		else
			System.out.println(" yet to join ");
			
		DateTimeFormatter dateform=DateTimeFormatter.ofPattern("dd/MM/yyyy");
				
		
					System.out.println(	today.format(dateform));
				
					LocalDate firstday=today.with(TemporalAdjusters.firstDayOfNextMonth());
			//		System.out.println(firstday);
					
					LocalDate lastday=firstday.with(TemporalAdjusters.lastDayOfMonth());
				//	System.out.println(lastday);
					
	List<LocalDate> nextmonsundays=		IntStream.iterate(0,i->i+1).limit(27).mapToObj(num->firstday.plusDays(num))
										.filter( dt->dt.getDayOfWeek().toString().equals("SUNDAY"))
										.collect(Collectors.toList());
	
	nextmonsundays.forEach(System.out::println);
	
	}

}
