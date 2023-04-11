
package com.stackroute.patientjpa.aopconfig;

import java.time.LocalDateTime;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import com.stackroute.patientjpa.model.Patient;

@Aspect
public class AspectLogs {
	
	Logger mylog=LoggerFactory.getLogger(AspectLogs.class);
	
	@Before("execution (* com.stackroute.patientjpa.controller.PatientController.viewpatients(..))")
	public void beforeView(JoinPoint jp)
	{
		mylog.info("View patients is being accessed ..");
	}
	
	
	@Around("saveemppoint()")

	public Object addEmployeeadvice(ProceedingJoinPoint proceedobj) throws Throwable
	{
	 Object obj=proceedobj.proceed();
	 try
	 {
		 ResponseEntity response=(ResponseEntity) obj;
		 
		 
		 
		 Patient patobj=(Patient) response.getBody();
		 mylog.info("New Patient named  " + patobj.getPatientName() + " Got added on  " + LocalDateTime.now() );
	 }
	 catch(Exception e)
	 {
		 
	 }
	 return obj;
	}
	
	@After("saveemppoint()")
	public void afterAdvice(JoinPoint jp)
	{
		mylog.info("After saving employee successfully , logs created");
	}
	
	@AfterThrowing(pointcut="saveemppoint()",throwing="excepobj")
	public void handleexc( Exception excepobj)
	{
		mylog.warn("Exception is raised while adding Patient " + excepobj.getMessage());
	}
	
	
	@Pointcut("execution (* com.stackroute.patientjpa.controller.PatientController.addpatient(..))")
	public void saveemppoint()
	{
		
	}
	

}
