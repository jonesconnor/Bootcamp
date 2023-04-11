package com.stackroute.streams;

import java.time.DayOfWeek;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DateTimeUtility {
	
	public DateTimeUtility() {
		
	}
	
	public List<String> getNextMonthWorkingDays(LocalDate today) {
		
		if (today == null) {
			throw new NullPointerException();
		}
		
		List<String> weekdays = new ArrayList<>();
		LocalDate firstDayOfFeb = LocalDate.of(2020, Month.FEBRUARY, 1);
		int totalDays = firstDayOfFeb.lengthOfMonth();
		
		for (int i = 0; i < totalDays; i++) {
			LocalDate date = firstDayOfFeb.plusDays(i);
			DayOfWeek dayOfWeek = date.getDayOfWeek();
			if (dayOfWeek.getValue() < 6) {
				weekdays.add(date.format(DateTimeFormatter.ofPattern("YYYY-MM-dd")));
			}
		}
		
		return weekdays;
	}

	public List<String> getScheduledBusDepartureTimings(String startTime, Duration frequency) {
		if (frequency == null || startTime == null) {
			throw new NullPointerException();
		}
		
		if (frequency.compareTo(Duration.ofHours(23)) > 0) {
			return new ArrayList<String>();
		}
		
		List<String> departureTimes = new ArrayList<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		LocalTime localTime = LocalTime.parse(startTime, formatter);
		
		while (localTime.isBefore(LocalTime.MAX)) {
			departureTimes.add(localTime.format(formatter));
			localTime = localTime.plus(frequency);
			if (departureTimes.get(departureTimes.size()-1).compareTo(localTime.toString()) > 0) {
				break;
			}
		}
		
		return departureTimes;
	}
	
}
