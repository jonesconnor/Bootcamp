package com.cgi.booking.service;

import java.time.LocalDate;
import java.util.List;

import com.cgi.booking.exception.IdConflictException;
import com.cgi.booking.exception.IdNotFoundException;
import com.cgi.booking.model.Booking;
import com.cgi.booking.model.TimeSlot;

public interface BookingService {

	Booking createBooking(Booking booking) throws IdConflictException;
	List<Booking> viewAllBooking();										
	Booking viewBooking(String id) throws IdNotFoundException;					
	Booking updateBooking(Booking booking) throws IdNotFoundException;		
	Booking deleteBooking(String id) throws IdNotFoundException;		
	List<Booking> viewBookingByServiceProvider(String id);	
	List<Booking> viewBookingByPatient(String id);		
	List<Booking> viewBookingByServiceProviderAndDate(String id,LocalDate date);	
	List<Booking> viewBookingByPatientAndDate(String id, LocalDate date);
	Booking updateBookingTimeSlot(String id,TimeSlot time) throws IdNotFoundException;
	List<Booking> viewBookingByDate(LocalDate date);
	List<Booking> viewBookingByPaymentToken(String token);
	List<Booking> viewBookingByLocation(String location);
	List<Booking> viewBookingByStatus(int status);
	List<Booking> viewBookingByEmergency(boolean emergency);
	List<Booking> viewBookingByPatientAndProvider(String patid,String provid);
}
