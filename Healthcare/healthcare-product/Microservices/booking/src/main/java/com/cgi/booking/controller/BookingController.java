package com.cgi.booking.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgi.booking.exception.IdConflictException;
import com.cgi.booking.exception.IdNotFoundException;
import com.cgi.booking.model.Booking;
import com.cgi.booking.model.TimeSlot;
import com.cgi.booking.service.BookingService;

@RestController
@RequestMapping("bookings")
public class BookingController {
	
	@Autowired
	BookingService service;

	public BookingController(BookingService service) {
	}
	
								
	@PostMapping("/create") 	//  Create Booking (POST /bookings/create)
	public ResponseEntity<?> createBooking( @RequestBody Booking booking) {
		Booking res;
		try {
			res = service.createBooking(booking);
			return new ResponseEntity<Booking>(res,HttpStatus.CREATED);
		} catch (IdConflictException e) {
			return new ResponseEntity<String>("Unable to create booking: "+e.getMessage(),HttpStatus.CONFLICT);
		}	
	}

	@GetMapping("all")	//	View all Bookings (GET /bookings/all)
	public ResponseEntity<?> viewAllBooking(){
		List<Booking> res = service.viewAllBooking();
		return new ResponseEntity<List>(res,HttpStatus.FOUND);
	}

	@GetMapping("/view/{id}")	//	View One Booking (GET /bookings/{id})
	public ResponseEntity<?> viewBookings(@PathVariable("id") String id1) throws IdNotFoundException{
		Booking res;
		try {
			//System.out.println(id);
			res = service.viewBooking(id1);
			//System.out.println(res);
			return new ResponseEntity<Booking>(res,HttpStatus.OK);
		} catch (IdNotFoundException e) {
			return new ResponseEntity<String>("Unable to view booking: "+e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("update") //	Update Booking Info (PUT /bookings/update)
	public ResponseEntity<?> updateBooking( @RequestBody Booking booking){
		Booking res;
		try {
			res = service.updateBooking(booking);
			return new ResponseEntity<Booking>(res,HttpStatus.OK);
		} catch (IdNotFoundException e) {
			return new ResponseEntity<String>("Unable to update booking: "+e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("{id}") //	Delete Booking (DELETE /bookings/{id})
	public ResponseEntity<?> deleteBooking( @PathVariable("id") String id){
		Booking res;
		try {
			res = service.deleteBooking(id);
			return new ResponseEntity<Booking>(res,HttpStatus.OK);
		} catch (IdNotFoundException e) {
			return new ResponseEntity<String>("Unable to update booking: "+e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("provider/{id}") //	View bookings for doctor/Caregiver (GET/bookings/provider/{id})
	public ResponseEntity<?> viewBookingByProvider( @PathVariable("id") String id){
		List<Booking> res;
		res = service.viewBookingByServiceProvider(id);
		return new ResponseEntity<List>(res,HttpStatus.OK);
	}
	
	@GetMapping("patient/{id}") //	View bookings for patients( GET /bookings/patient/{id})
	public ResponseEntity<?> viewBookingByPatient( @PathVariable("id") String id){
		List<Booking> res;
		res = service.viewBookingByPatient(id);
		return new ResponseEntity<List>(res,HttpStatus.OK);
	}
	
	@PutMapping("update/timeslot/{id}")//	Update Booking TimeSlot (PUT /bookings/update/timeslot/{id})
	public ResponseEntity<?> updateTimeSlot( @PathVariable("id") String id, @RequestBody TimeSlot timeslot){
		Booking res;
		try {
			res = service.updateBookingTimeSlot(id, timeslot);
			return new ResponseEntity<Booking>(res,HttpStatus.OK);
		} catch (IdNotFoundException e) {
			return new ResponseEntity<String>("Unable to update booking: "+e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("patient/{id}/date/{date}")	//	View Bookings by patient and date (GET /bookings/patient/{id}/date/{date})
	public ResponseEntity<?> viewBookingPatientDate(@PathVariable("id") String id ,@PathVariable("date") LocalDate date){
		List<Booking> res = service.viewBookingByPatientAndDate(id, date);
		return new ResponseEntity<List>(res,HttpStatus.OK);
	}
	
	@GetMapping("provider/{id}/date/{date}")	//	View Bookings by date and provider (GET /bookings/provider/{id}/date/{date})
	public ResponseEntity<?> viewBookingProviderDate(@PathVariable("id") String id ,@PathVariable("date") LocalDate date){
		List<Booking> res = service.viewBookingByServiceProviderAndDate(id, date);
		return new ResponseEntity<List>(res,HttpStatus.OK);
	}
	
	@GetMapping("date/{date}")	//	View Bookings by date (GET /bookings/date/{date})
	public ResponseEntity<?> viewBookingDate(@PathVariable("date") LocalDate date){
		List<Booking> res = service.viewBookingByDate(date);
		return new ResponseEntity<List>(res,HttpStatus.OK);
	}
	
	@GetMapping("payment/{token}")	//	View Bookings by payment token (GET /bookings/payment/{token})
	public ResponseEntity<?> viewBookingPayment(@PathVariable("token") String token){
		List<Booking> res = service.viewBookingByPaymentToken(token);
		return new ResponseEntity<List>(res,HttpStatus.OK);
	}
	
	@GetMapping("location/{location}")	//	View Bookings by location (GET /bookings/location/{location})
	public ResponseEntity<?> viewBookingLocation(@PathVariable("location") String location){
		List<Booking> res = service.viewBookingByLocation(location);
		return new ResponseEntity<List>(res,HttpStatus.OK);
	}
	
	@GetMapping("status/{status}")	//	View Bookings by status (GET /bookings/status/{status})
	public ResponseEntity<?> viewBookingStatus(@PathVariable("status") int status){
		List<Booking> res = service.viewBookingByStatus(status);
		return new ResponseEntity<List>(res,HttpStatus.OK);
	}

	@GetMapping("emergency/{emergency}")	//	View emergency Bookings (GET /bookings/emergency/{emergency})
	public ResponseEntity<?> viewBookingEmergency(@PathVariable("emergency") boolean emergency){
		List<Booking> res = service.viewBookingByEmergency(emergency);
		return new ResponseEntity<List>(res,HttpStatus.OK);
	}
	
	@GetMapping("patient/{patientid}/provider/{providerid}")	//	View Bookings by patient and provider (GET /bookings/patient/{patientid}/provider/{providerid})
	public ResponseEntity<?> viewBookingPatientProvider(@PathVariable("patientid") String patid, @PathVariable("providerid") String provid){
		List<Booking> res = service.viewBookingByPatientAndProvider(patid, provid);
		return new ResponseEntity<List>(res,HttpStatus.OK);
	}
//	
//	@GetMapping("provider/{id}/{start}/{end}") //	View bookings for doctor/Caregiver (GET/bookings/provider/{id}/{start}/{end})
//	public ResponseEntity<?> viewBookingByProviderAndDateBetween( @PathVariable("id") int id, @PathVariable("start") String start, @PathVariable("end") String end){
//		List<Booking> res;
//		SimpleDateFormat formatter = new SimpleDateFormat("YYYY-mm-dd'T'HH:MM:ss", Locale.ENGLISH);
//		try {
//			res = service.viewBookingByServiceProviderAndDateBetween(id, formatter.parse(start), formatter.parse(end));
//		} catch (ParseException e) {
//			return new ResponseEntity<String>("Incorrect date format",HttpStatus.BAD_REQUEST);
//		}
//		return new ResponseEntity<List>(res,HttpStatus.OK);
//	}
}
