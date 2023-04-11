package com.cgi.booking.aspect;

import java.time.LocalDateTime;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import com.cgi.booking.model.Booking;

@Aspect
public class AspectLogs {
	
	Logger mylog=LoggerFactory.getLogger(AspectLogs.class);
		
	@Around("createbookingpoint()")
	public Object createBookingAdvice(ProceedingJoinPoint proceedobj) throws Throwable
	{
		 Object obj=proceedobj.proceed();
		 try
		 {
			 ResponseEntity response=(ResponseEntity) obj;
			 Booking bookobj=(Booking) response.getBody();
			 mylog.info("New booking " + bookobj.getId()+ " created on " + LocalDateTime.now() );
		 }
		 catch(Exception e)
		 {mylog.info("Invalid booking creation attempted on " + LocalDateTime.now() );}
		 return obj;
	}
	
	@AfterThrowing(pointcut="createbookingpoint()",throwing="excepobj")
	public void handleexccreate( Exception excepobj)
	{
		mylog.info("Exception raised while creating a booking" + excepobj.getMessage());
	}

	@Pointcut(value ="execution(* com.cgi.booking.controller.BookingController.createBooking(..))")
	public void createbookingpoint(){}
	
	@Around("deletebookingpoint()")
	public Object deleteBookingAdvice(ProceedingJoinPoint proceedobj) throws Throwable
	{
		 Object obj=proceedobj.proceed();
		 try
		 {
			 ResponseEntity response=(ResponseEntity) obj;
			 Booking bookobj=(Booking) response.getBody();
			 mylog.info("Booking " + bookobj.getId()+ " deleted on " + LocalDateTime.now() );
		 }
		 catch(Exception e)
		 {mylog.info("Invalid booking deletion attempted on " + LocalDateTime.now() );}
		 return obj;
	}
	
	@AfterThrowing(pointcut="deletebookingpoint()",throwing="excepobj")
	public void handleexcdelete( Exception excepobj)
	{
		mylog.info("Exception raised while deleting a booking" + excepobj.getMessage());
	}
	
	@Pointcut(value ="execution(* com.cgi.booking.controller.BookingController.deleteBooking(..))")
	public void deletebookingpoint(){}
	
	@Around("updatebookingpoint() || updatebookingpoint2()")
	public Object updateBookingAdvice(ProceedingJoinPoint proceedobj) throws Throwable
	{
		 Object obj=proceedobj.proceed();
		 try
		 {
			 ResponseEntity response=(ResponseEntity) obj;
			 Booking bookobj=(Booking) response.getBody();
			 mylog.info("Booking " + bookobj.getId()+ " updated on " + LocalDateTime.now() );
		 }
		 catch(Exception e)
		 {mylog.info("Invalid booking update attempted on " + LocalDateTime.now() );}
		 return obj;
	}
	
	@AfterThrowing(pointcut="updatebookingpoint() || updatebookingpoint2() ",throwing="excepobj")
	public void handleexcupdate( Exception excepobj)
	{
		mylog.info("Exception raised while updating a booking" + excepobj.getMessage());
	}
	
	@Pointcut(value ="execution(* com.cgi.booking.controller.BookingController.updateBooking(..))")
	public void updatebookingpoint(){}
	
	@Pointcut(value ="execution(* com.cgi.booking.controller.BookingController.updateTimeSlot(..))")
	public void updatebookingpoint2(){}
}
