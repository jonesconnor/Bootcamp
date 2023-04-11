package com.cgi.authentication.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.cgi.authentication.model.User;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Slf4j
@Component
public class LoggingAspect {
	
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(LoggingAspect.class);
	@Pointcut("within(com.cgi..*))")
    public void loggingPointCut(){}	

	@Around("loggingPointCut()")
    public Object before(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Method Call: "+joinPoint.getSignature());
        Object object = joinPoint.proceed();
        if(object instanceof User){
        	int firstComma =joinPoint.getArgs()[0].toString().indexOf(",");
        	if(firstComma!=-1) {
        		String mailid =joinPoint.getArgs()[0].toString().substring(6,firstComma);        	
            	log.info("User Information: "+mailid);    
        	}
        }	
        return object;
    }
}
