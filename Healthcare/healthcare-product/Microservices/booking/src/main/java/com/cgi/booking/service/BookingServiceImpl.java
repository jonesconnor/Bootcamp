package com.cgi.booking.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgi.booking.exception.IdConflictException;
import com.cgi.booking.exception.IdNotFoundException;
import com.cgi.booking.model.Booking;
import com.cgi.booking.model.TimeSlot;
import com.cgi.booking.repository.BookingRepository;

@Service
public class BookingServiceImpl implements BookingService{

	private final BookingRepository repo;

    @Autowired
    public BookingServiceImpl(BookingRepository repo){
        this.repo =repo;
    }
	
	@Override
	public Booking createBooking(Booking booking) throws IdConflictException{
//		System.out.println(booking); 
//		this.checkIdNotExists(booking.getId());
//		return repo.save(booking);
//		 
//		try {
//			this.checkIdExists(booking.getId());
//			throw new IdConflictException("Booking Id already exists.");
//		}catch(IdNotFoundException e) {
			return repo.save(booking);
		//}
	}
	
	
//	public boolean checkIdNotExists(int id) throws IdConflictException{
//		Optional<Booking> opt = repo.findById(id);
//		if(opt.isPresent())
//			throw new IdConflictException("Booking Id Already found.");
//		else
//			return true;
//	}
	
	private Booking checkIdExists(String id) throws IdNotFoundException{
		Optional<Booking> opt = repo.findById(id);
		if(opt.isPresent())
			return opt.get();
		
		throw new IdNotFoundException("No booking found with the given Id.");
	}

	@Override
	public List<Booking> viewAllBooking() {
		return repo.findAll();
	}

	@Override
	public Booking viewBooking(String id) throws IdNotFoundException {
		return this.checkIdExists(id);
	}

	@Override
	public Booking updateBooking(Booking booking) throws IdNotFoundException {
		this.checkIdExists(booking.getId());
		return repo.save(booking);
	}

	@Override
	public Booking deleteBooking(String id) throws IdNotFoundException{
		Booking deleted = this.checkIdExists(id);
		repo.deleteById(id);
		return deleted;
	}

	@Override
	public List<Booking> viewBookingByServiceProvider(String id) {
		return repo.findByServiceProviderId(id);
	}

	@Override
	public List<Booking> viewBookingByPatient(String id) {
		return repo.findByPatientId(id);
	}

	@Override
	public Booking updateBookingTimeSlot(String id,TimeSlot time) throws IdNotFoundException {
		Booking booking = this.checkIdExists(id);
		booking.setTimeSlot(time);
		return repo.save(booking);
	}

	@Override
	public List<Booking> viewBookingByServiceProviderAndDate(String id, LocalDate date) {
		return repo.findByServiceProviderIdAndDate(id, date);
	}

	@Override
	public List<Booking> viewBookingByPatientAndDate(String id, LocalDate date) {
		return repo.findByPatientIdAndDate(id, date);
	}

	@Override
	public List<Booking> viewBookingByDate(LocalDate date) {
		return repo.findByDate(date);
	}

	@Override
	public List<Booking> viewBookingByPaymentToken(String token) {
		return repo.findByPaymentToken(token);
	}

	@Override
	public List<Booking> viewBookingByLocation(String location) {
		return repo.findByLocation(location);
	}

	@Override
	public List<Booking> viewBookingByStatus(int status) {
		return repo.findByStatus(status);
	}

	@Override
	public List<Booking> viewBookingByEmergency(boolean emergency) {
		return repo.findByIsEmergency(emergency);
	}

	@Override
	public List<Booking> viewBookingByPatientAndProvider(String patid, String provid) {
		return repo.findByPatientIdAndServiceProviderId(patid, provid);
	}
}
