package com.cgi.booking.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.cgi.booking.model.Booking;

@Repository
public interface BookingRepository  extends MongoRepository<Booking, String>{
	
	List<Booking> findByServiceProviderId(String id);
	List<Booking> findByPatientId(String id);
	List<Booking> findByLocation(String location);
	List<Booking> findByIsEmergency(boolean emergency);
	List<Booking> findByStatus(int status);
	List<Booking> findByPaymentToken(String token);
	List<Booking> findByPatientIdAndServiceProviderId(String patid, String provid);
	
	@Query("{ 'serviceProviderId' : ?0, 'TimeSlot.BookingDate':?1}")
	List<Booking> findByServiceProviderIdAndDate(String id, LocalDate date);
	
	@Query("{ 'patientId' : ?0, 'TimeSlot.BookingDate':?1}")
	List<Booking> findByPatientIdAndDate(String id, LocalDate date);
	
	@Query("{ 'TimeSlot.BookingDate' : ?0}")
	List<Booking> findByDate(LocalDate date);

}
