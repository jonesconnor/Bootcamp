package com.cgi.booking.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cgi.booking.exception.IdConflictException;
import com.cgi.booking.exception.IdNotFoundException;
import com.cgi.booking.model.Booking;
import com.cgi.booking.model.TimeSlot;
import com.cgi.booking.repository.BookingRepository;
import com.cgi.booking.service.BookingService;
import com.cgi.booking.service.BookingServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BookingControllerTest {
	
	@InjectMocks
	private BookingController controller;
	
	@Mock
	private BookingService service;
	
	private MockMvc mockmvc;
     
	private Booking booking;
	private Booking booking2;
	private List<Booking> bookings=new ArrayList<Booking>();
 
    @BeforeEach
     public void init()
     {
    	 MockitoAnnotations.openMocks(this);
    	 mockmvc=MockMvcBuilders.standaloneSetup(controller).build();
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
     
    private String convertObject(Object obj) throws JsonProcessingException
    {
   	 	ObjectMapper objmapper=new ObjectMapper();
   	 	objmapper.registerModule(new JavaTimeModule());
   	 	return objmapper.writeValueAsString(obj);
    }
    
    @Test
    public void whenPostObjectSuccess() throws  Exception
    {
    	Mockito.when(service.createBooking(booking)).thenReturn(booking);
    	mockmvc.perform(MockMvcRequestBuilders.post("/bookings/create")
    			 .contentType(MediaType.APPLICATION_JSON)
    			 .content(convertObject(booking)))
    			 .andExpect(MockMvcResultMatchers.status().isCreated());
     }
     
//removed since id is now auto-generated
//     @Test
//     public void whenPostObjectThenFailed() throws  Exception
//     {
//    	 Mockito.when(service.createBooking(any())).thenThrow(IdConflictException.class);
//    	 mockmvc.perform(MockMvcRequestBuilders.post("/bookings/create")
//    			 .contentType(MediaType.APPLICATION_JSON)
//    			 .content(convertObject(booking)))
//    			 .andExpect(MockMvcResultMatchers.status().isConflict()); 
//     }
     
     @Test
     public void whenGetAllSuccess() throws Exception
     {
     	Mockito.when(service.viewAllBooking()).thenReturn(bookings);
     	mockmvc.perform(MockMvcRequestBuilders.get("/bookings/all")
     			 .contentType(MediaType.APPLICATION_JSON))
     			 .andExpect(MockMvcResultMatchers.status().isFound());
      }
     
     @Test
     public void whenGetOneSuccess() throws Exception
     {
     	Mockito.when(service.viewBooking(booking.getId())).thenReturn(booking);
     	mockmvc.perform(MockMvcRequestBuilders.get("/bookings/view/{bookingid}", booking.getId())
     			 .contentType(MediaType.APPLICATION_JSON))
     			 .andExpect(MockMvcResultMatchers.status().isOk());
      }
     
     @Test
     public void whenGetOneFailure() throws Exception
     {
     	Mockito.when(service.viewBooking(any())).thenThrow(IdNotFoundException.class);
     	mockmvc.perform(MockMvcRequestBuilders.get("/bookings/view/{bookingid}", "101")
     			 .contentType(MediaType.APPLICATION_JSON))
     			 .andExpect(MockMvcResultMatchers.status().isNotFound());
      }
     
     @Test
     public void whenPutUpdateSuccess() throws Exception
     {
     	Mockito.when(service.updateBooking(booking)).thenReturn(booking);
     	mockmvc.perform(MockMvcRequestBuilders.put("/bookings/update")
     			 .contentType(MediaType.APPLICATION_JSON)
     			 .content(convertObject(booking)))
     			 .andExpect(MockMvcResultMatchers.status().isOk());
      }
     
     @Test
     public void whenPutUpdateFailure() throws Exception
     {
     	Mockito.when(service.updateBooking(any())).thenThrow(IdNotFoundException.class);
     	mockmvc.perform(MockMvcRequestBuilders.put("/bookings/update")
    			 .contentType(MediaType.APPLICATION_JSON)
    			 .content(convertObject(booking)))
    			 .andExpect(MockMvcResultMatchers.status().isNotFound());
      }
     
     @Test
     public void whenPutUpdateTimeSlotSuccess() throws Exception
     {
     	Mockito.when(service.updateBookingTimeSlot(booking.getId(),booking.getTimeSlot())).thenReturn(booking);
     	mockmvc.perform(MockMvcRequestBuilders.put("/bookings/update/timeslot/{id}", booking.getId())
     			 .contentType(MediaType.APPLICATION_JSON)
     			 .content(convertObject(booking.getTimeSlot())))
     			 .andExpect(MockMvcResultMatchers.status().isOk());
      }
     
     @Test
     public void whenPutUpdateTimeSlotFailure() throws Exception
     {
     	Mockito.when(service.updateBookingTimeSlot(any(), any())).thenThrow(IdNotFoundException.class);
     	mockmvc.perform(MockMvcRequestBuilders.put("/bookings/update/timeslot/{id}", booking.getId())
    			 .contentType(MediaType.APPLICATION_JSON)
    			 .content(convertObject(booking.getTimeSlot())))
    			 .andExpect(MockMvcResultMatchers.status().isNotFound());
      }
     
     @Test
     public void deleteBookingSuccess() throws Exception
     {
    	 Mockito.when(service.deleteBooking("0")).thenReturn(booking);
    	 
    	 mockmvc.perform(MockMvcRequestBuilders.delete("/bookings/0")
    			 .contentType(MediaType.APPLICATION_JSON))
    	 		 .andExpect(MockMvcResultMatchers.status().isOk())
    	 		 .andDo(MockMvcResultHandlers.print());
     }
     
     @Test
     public void deleteBookingFailure() throws Exception
     {
    	 String badid = "100";
    	 Mockito.when(service.deleteBooking(any())).thenThrow(new IdNotFoundException("invalid id"));
    	 mockmvc.perform(MockMvcRequestBuilders.delete("/bookings/{bad_id}", badid)
    			.contentType(MediaType.APPLICATION_JSON))
    	 		.andExpect(MockMvcResultMatchers.status().isNotFound())
    	 		.andDo(MockMvcResultHandlers.print()); 
     }
     
     @Test
     public void whenGetByPatientSuccess() throws Exception
     {
     	Mockito.when(service.viewBookingByPatient(booking.getPatientId())).thenReturn(bookings);
     	mockmvc.perform(MockMvcRequestBuilders.get("/bookings/patient/{id}",booking.getPatientId() )
     			 .contentType(MediaType.APPLICATION_JSON)
     			 .content(convertObject(bookings)))
     			 .andExpect(MockMvcResultMatchers.status().isOk());
      }
     
     @Test
     public void whenGetByProviderSuccess() throws Exception
     {
     	Mockito.when(service.viewBookingByServiceProvider(booking.getServiceProviderId())).thenReturn(bookings);
     	mockmvc.perform(MockMvcRequestBuilders.get("/bookings/provider/{id}",booking.getServiceProviderId() )
     			 .contentType(MediaType.APPLICATION_JSON))
     			 .andExpect(MockMvcResultMatchers.status().isOk());
      }
     
     @Test
     public void whenGetByPatientAndDateSuccess() throws Exception
     {
     	Mockito.when(service.viewBookingByPatientAndDate(booking.getPatientId(), booking.getTimeSlot().getBookingDate())).thenReturn(bookings);
     	mockmvc.perform(MockMvcRequestBuilders.get("/bookings/patient/{id}/date/{date}",booking.getPatientId(), booking.getTimeSlot().getBookingDate() )
     			 .contentType(MediaType.APPLICATION_JSON))
     			 .andExpect(MockMvcResultMatchers.status().isOk());
      }
     
     @Test
     public void whenGetByProviderAndDateSuccess() throws Exception
     {
     	Mockito.when(service.viewBookingByServiceProviderAndDate(booking.getServiceProviderId(), booking.getTimeSlot().getBookingDate())).thenReturn(bookings);
     	mockmvc.perform(MockMvcRequestBuilders.get("/bookings/provider/{id}/date/{date}",booking.getServiceProviderId(), booking.getTimeSlot().getBookingDate() )
     			 .contentType(MediaType.APPLICATION_JSON))
     			 .andExpect(MockMvcResultMatchers.status().isOk());
      }
     
     @Test
     public void whenGetByDate() throws Exception
     {
     	Mockito.when(service.viewBookingByDate(booking.getTimeSlot().getBookingDate())).thenReturn(bookings);
     	mockmvc.perform(MockMvcRequestBuilders.get("/bookings/date/{date}",booking.getTimeSlot().getBookingDate())
     			 .contentType(MediaType.APPLICATION_JSON))
     			 .andExpect(MockMvcResultMatchers.status().isOk());
      }
     
     @Test
     public void whenGetByPaymetnToken() throws Exception
     {
     	Mockito.when(service.viewBookingByPaymentToken(booking.getPaymentToken())).thenReturn(bookings);
     	mockmvc.perform(MockMvcRequestBuilders.get("/bookings/payment/{token}",booking.getPaymentToken())
     			 .contentType(MediaType.APPLICATION_JSON))
     			 .andExpect(MockMvcResultMatchers.status().isOk());
      }

     @Test
     public void whenGetByLocation() throws Exception
     {
     	Mockito.when(service.viewBookingByLocation(booking.getLocation())).thenReturn(bookings);
     	mockmvc.perform(MockMvcRequestBuilders.get("/bookings/location/{location}",booking.getLocation())
     			 .contentType(MediaType.APPLICATION_JSON))
     			 .andExpect(MockMvcResultMatchers.status().isOk());
      }
     
     @Test
     public void whenGetByStatus() throws Exception
     {
     	Mockito.when(service.viewBookingByStatus(booking.getStatus())).thenReturn(bookings);
     	mockmvc.perform(MockMvcRequestBuilders.get("/bookings/status/{status}",booking.getStatus())
     			 .contentType(MediaType.APPLICATION_JSON))
     			 .andExpect(MockMvcResultMatchers.status().isOk());
      }
     
     @Test
     public void whenGetByEmergency() throws Exception
     {
     	Mockito.when(service.viewBookingByEmergency(booking.isEmergency())).thenReturn(bookings);
     	mockmvc.perform(MockMvcRequestBuilders.get("/bookings/emergency/{emergency}",booking.isEmergency())
     			 .contentType(MediaType.APPLICATION_JSON))
     			 .andExpect(MockMvcResultMatchers.status().isOk());
      }
     
     @Test
     public void whenGetByPatientAndProvider() throws Exception
     {
     	Mockito.when(service.viewBookingByPatientAndProvider(booking.getPatientId(), booking.getServiceProviderId())).thenReturn(bookings);
     	mockmvc.perform(MockMvcRequestBuilders.get("/bookings/patient/{patientid}/provider/{providerid}",booking.getPatientId(), booking.getServiceProviderId())
     			 .contentType(MediaType.APPLICATION_JSON))
     			 .andExpect(MockMvcResultMatchers.status().isOk());
      }

}
