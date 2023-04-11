package com.stackroute.java8sess3.datetime;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class SampleTime {

	public static void main(String[] args) throws Exception {
		
		LocalTime currtime=LocalTime.now();
		
		System.out.println(currtime);
		
		LocalTime aftertwo=currtime.plusHours(2);
		
	
		LocalTime traintime=LocalTime.of(9, 15, 24);
		
//		for (int i=0;i<=10;i++)
//		{
//			System.out.println(i);
//			Thread.sleep(2000);
//		}
//		
		
		LocalTime currtime2=LocalTime.now();
		
		Duration duration=Duration.between(currtime, currtime2);
		
		System.out.println(duration.getNano());
		
		
		DateTimeFormatter dt=DateTimeFormatter.ofPattern("HH:mm:ss");
		System.out.println(currtime2.format(dt));
		
		LocalDateTime ldt=LocalDateTime.now();
		System.out.println(ldt);
	
		
		ZonedDateTime currzone=ZonedDateTime.now();
		System.out.println(currzone.getZone());
		System.out.println("Time in India " + currzone);
		
		ZoneId zoneid=ZoneId.of("America/Edmonton");
		ZonedDateTime aus=ZonedDateTime.now(zoneid);
		
		System.out.println("Time at Canada " + aus);
		
		
		
		
		
	}
}
