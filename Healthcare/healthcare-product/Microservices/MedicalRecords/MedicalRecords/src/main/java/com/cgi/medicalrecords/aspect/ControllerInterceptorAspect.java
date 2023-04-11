package com.cgi.medicalrecords.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Slf4j
@Component
public class ControllerInterceptorAspect {
	
	private static final Logger logger = LoggerFactory.getLogger(ControllerInterceptorAspect.class);
	
	@AfterReturning(value = "execution(* com.cgi.medicalrecords.controller.RecordController.addRecord(..))", returning = "response")
    public void logSuccessfulAddRecord(JoinPoint joinPoint, ResponseEntity<?> response) {
        String method = joinPoint.getSignature().toShortString();
        int status = response.getStatusCode().value();
        logger.info("Method " + method + " completed successfully with status code " + status);
    }

    @AfterThrowing(value = "execution(* com.cgi.medicalrecords.controller.RecordController.addRecord(..))", throwing = "exception")
    public void logExceptionInAddRecord(JoinPoint joinPoint, Throwable exception) {
        String method = joinPoint.getSignature().toShortString();
        logger.error("An exception occurred in method " + method + " : " + exception.getMessage());
    }
}
