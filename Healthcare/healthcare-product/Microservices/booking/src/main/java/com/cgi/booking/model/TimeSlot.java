package com.cgi.booking.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TimeSlot {
	LocalDate bookingDate;
	LocalTime bookingTime;
	int duration; //(in minutes)
	
	public TimeSlot() {
	}

	public TimeSlot(LocalDate bookingDate, LocalTime bookingTime, int duration) {
		this.bookingDate = bookingDate;
		this.bookingTime = bookingTime;
		this.duration = duration;
	}

	public LocalDate getBookingDate() {
		return this.bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}

	public LocalTime getBookingTime() {
		return this.bookingTime;
	}

	public void setBookingTime(LocalTime bookingTime) {
		this.bookingTime = bookingTime;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	
}
