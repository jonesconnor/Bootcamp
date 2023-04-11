package com.cgi.booking.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.cgi.booking.exception.IdConflictException;
import com.cgi.booking.exception.IdNotFoundException;
import com.cgi.booking.model.Booking;
import com.cgi.booking.model.TimeSlot;
import com.cgi.booking.repository.BookingRepository;

public class BookingServiceTest {

	@InjectMocks
	private BookingServiceImpl service;
	
	@Mock
	private BookingRepository repo;

	private Booking booking;
	private Booking booking2;
	
	private List<Booking> bookings=new ArrayList<Booking>();
	
	@BeforeEach
	public void setup()
	{
		MockitoAnnotations.openMocks(this);
		booking =new Booking("0","10",false, "20", "check up",
				"payment string", new TimeSlot(LocalDate.now(), LocalTime.now(), 60), "online", 1, "none",
				0, LocalDate.now());
			
		Booking booking1=new Booking("1","11",false, "21", "cold",
				"payment string", new TimeSlot(LocalDate.now(), LocalTime.now(), 30), "online", 1, "none",
				0, LocalDate.now());
		booking2=new Booking("2","12",true, "22", "urgent prescription",
				"payment string", new TimeSlot(LocalDate.now(), LocalTime.NOON, 120), "online", 1, "none",
				0, LocalDate.now());
		
		bookings.add(booking);
		bookings.add(booking1);
		bookings.add(booking2);
	}
	
	@Test
	public void whenCreateBookingStoredsuccess() throws Exception
	{
		Mockito.when(repo.save(booking)).thenReturn(booking);
		Booking result=service.createBooking(booking);
		assertEquals(result,booking);
		assertEquals(result.getSubject(),"check up");
		verify(repo,times(1)).save(booking);
		
	}

//removed since id is now auto-generated
//	@Test
//	public void whenCreateBookingFailedToStore()
//	{
//		Mockito.when(repo.findById(booking.getId())).thenReturn(Optional.of(booking));
//		assertThrows(IdConflictException.class,()->service.createBooking(booking));	
//	}
	
	
	@Test
	public void whenGetListViewAllObject()
	{
		Mockito.when(repo.findAll()).thenReturn(bookings);
		List<Booking> blist=service.viewAllBooking();
		assertEquals(blist,bookings);
	}
	
	@Test
	public void whenGetOneBookingSuccess() throws IdNotFoundException
	{
		Mockito.when(repo.findById(booking.getId())).thenReturn(Optional.of(booking));
		Booking result=service.viewBooking(booking.getId());
		assertEquals(result,booking);
		verify(repo,times(1)).findById(booking.getId());
	}
	
	@Test
	public void whenGetOneBookingFailure() throws IdNotFoundException
	{
		Mockito.when(repo.findById("100")).thenReturn(Optional.empty());
		assertThrows(IdNotFoundException.class,()->service.viewBooking("100"));	
	}
	
	@Test
	public void whenUpdateThenSuccess() throws IdNotFoundException, IdConflictException
	{
		Booking temp = booking2;
		temp.setEmergency(false);
		Mockito.when(repo.save(temp)).thenReturn(temp);
		Mockito.when(repo.findById(booking2.getId())).thenReturn(Optional.of(booking2));
		Booking result=	service.updateBooking(temp);
		assertEquals(result,temp);
		verify(repo,times(1)).save(temp);
	}
	
	@Test
	public void whenUpdateThenFailed()
	{
		 
		 Booking temp = new Booking("2","10",false, "20", "check up",
					"payment string", new TimeSlot(LocalDate.now(), LocalTime.now(), 60), "online", 1, "none",
					0, LocalDate.now());
		 Mockito.when(repo.findById(temp.getId())).thenReturn(Optional.empty());
		 assertThrows(IdNotFoundException.class,()-> service.updateBooking(temp));
	}
	
	@Test
	public void whenUpdateTimeSlotThenSuccess() throws IdNotFoundException, IdConflictException
	{
		TimeSlot temp = booking2.getTimeSlot();
		temp.setDuration(10);
		Booking tempbooking = booking2;
		tempbooking.setTimeSlot(temp);
		Mockito.when(repo.save(booking2)).thenReturn(tempbooking);
		Mockito.when(repo.findById(booking2.getId())).thenReturn(Optional.of(booking2));
		Booking result=	service.updateBookingTimeSlot(booking2.getId(), temp);
		assertEquals(result,tempbooking);
		verify(repo,times(1)).save(tempbooking);
	}
	
	@Test
	public void whenUpdateTimeSlotThenFailed()
	{
		Booking temp = new Booking("2","10",false, "20", "check up",
				"payment string", new TimeSlot(LocalDate.now(), LocalTime.now(), 60), "online", 1, "none",
				0, LocalDate.now());
		 Mockito.when(repo.findById(temp.getId())).thenReturn(Optional.empty());
		 assertThrows(IdNotFoundException.class,()-> service.updateBookingTimeSlot(temp.getId(), temp.getTimeSlot()));
	}
	
	
	@Test
	public void whenDeleteThenSuccess() throws IdNotFoundException
	{
		Mockito.when(repo.findById(booking.getId())).thenReturn(Optional.of(booking));
		Booking result=	service.deleteBooking(booking.getId());
		assertEquals(result,booking);
		verify(repo,times(1)).deleteById(booking.getId());
	}
	
	@Test
	public void whenDeleteThenFailed()
	{
		 Mockito.when(repo.findById(booking.getId())).thenReturn(Optional.empty());
		 assertThrows(IdNotFoundException.class,()-> service.deleteBooking(booking.getId()));
		 verify(repo,times(0)).deleteById(booking.getId());
	}

	@Test
	public void whenGetByProviderViewList()
	{
		Mockito.when(repo.findByServiceProviderId(booking.getServiceProviderId())).thenReturn(List.of(booking));
		List<Booking> blist=service.viewBookingByServiceProvider(booking.getServiceProviderId());
		assertEquals(blist,List.of(booking));
	}
	
	@Test
	public void whenGetByPatientViewList()
	{
		Mockito.when(repo.findByPatientId(booking.getPatientId())).thenReturn(List.of(booking));
		List<Booking> blist=service.viewBookingByPatient(booking.getPatientId());
		assertEquals(blist,List.of(booking));
	}
	
	@Test
	public void whenGetByProviderAndDate()
	{
		Mockito.when(repo.findByServiceProviderIdAndDate(booking.getServiceProviderId(), booking.getTimeSlot().getBookingDate())).thenReturn(List.of(booking));
		List<Booking> blist=service.viewBookingByServiceProviderAndDate(booking.getServiceProviderId(), booking.getTimeSlot().getBookingDate());
		assertEquals(blist,List.of(booking));
	}
	
	@Test
	public void whenGetByPatientAndDate()
	{
		Mockito.when(repo.findByPatientIdAndDate(booking.getPatientId(), booking.getTimeSlot().getBookingDate())).thenReturn(List.of(booking));
		List<Booking> blist=service.viewBookingByPatientAndDate(booking.getPatientId(), booking.getTimeSlot().getBookingDate());
		assertEquals(blist,List.of(booking));
	}
	
	@Test
	public void whenGetByDate()
	{
		Mockito.when(repo.findByDate(booking.getTimeSlot().getBookingDate())).thenReturn(bookings);
		List<Booking> blist=service.viewBookingByDate(booking.getTimeSlot().getBookingDate());
		assertEquals(blist,bookings);
	}
	
	@Test
	public void whenGetByPaymentToken()
	{
		Mockito.when(repo.findByPaymentToken(booking.getPaymentToken())).thenReturn(List.of(booking));
		List<Booking> blist=service.viewBookingByPaymentToken(booking.getPaymentToken());
		assertEquals(blist,List.of(booking));
	}
	
	@Test
	public void whenGetByLocation()
	{
		Mockito.when(repo.findByLocation(booking.getLocation())).thenReturn(List.of(booking));
		List<Booking> blist=service.viewBookingByLocation(booking.getLocation());
		assertEquals(blist,List.of(booking));
	}
	
	@Test
	public void whenGetByStatus()
	{
		Mockito.when(repo.findByStatus(booking.getStatus())).thenReturn(List.of(booking));
		List<Booking> blist=service.viewBookingByStatus(booking.getStatus());
		assertEquals(blist,List.of(booking));
	}
	
	@Test
	public void whenGetByEmergency()
	{
		Mockito.when(repo.findByIsEmergency(booking.isEmergency())).thenReturn(bookings);
		List<Booking> blist=service.viewBookingByEmergency(booking.isEmergency());
		assertEquals(blist,bookings);
	}
	
	@Test
	public void whenGetByPatientAndProvider()
	{
		Mockito.when(repo.findByPatientIdAndServiceProviderId(booking.getPatientId(), booking.getServiceProviderId())).thenReturn(List.of(booking));
		List<Booking> blist=service.viewBookingByPatientAndProvider(booking.getPatientId(), booking.getServiceProviderId());
		assertEquals(blist,List.of(booking));
	}

}
