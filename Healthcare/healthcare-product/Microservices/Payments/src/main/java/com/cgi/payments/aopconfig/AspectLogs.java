package com.cgi.payments.aopconfig;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class AspectLogs {
	
	Logger logs = LoggerFactory.getLogger(AspectLogs.class);
	
	@Pointcut("execution (* com.cgi.payments.controller.PaymentController.addPayment(..))")
	public void addPayment() {}
	
	@Pointcut("execution (* com.cgi.payments.controller.PaymentController.updatePayment(..))")
	public void updatePayment() {}
	
	@Pointcut("execution (* com.cgi.payments.controller.PaymentController.deletePayment(..))")
	public void deletePayment() {}
	

	@AfterThrowing(pointcut="addPayment()", throwing="operationException")
	public void handleThrowAdd( Exception operationException)
	{
		logs.warn("Exception is raised while adding a Payment " + operationException.getMessage());
	}

	@AfterThrowing(pointcut="updatePayment()", throwing="operationException")
	public void handleThrowUpdate( Exception operationException)
	{
		logs.warn("Exception is raised while adding a Payment " + operationException.getMessage());
	}
	
	@AfterThrowing(pointcut="deletePayment()", throwing="operationException")
	public void handleThrowDelete( Exception operationException)
	{
		logs.warn("Exception is raised while adding a Payment " + operationException.getMessage());
	}
}
