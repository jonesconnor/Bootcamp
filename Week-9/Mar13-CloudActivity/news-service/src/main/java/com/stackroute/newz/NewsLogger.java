package com.stackroute.newz;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Slf4j
@Component
public class NewsLogger {
	
	private static final Logger logger = LoggerFactory.getLogger(NewsLogger.class);
	
	@Pointcut("within(com.stackroute.newz.controller.NewsController)")
	public void loggingPointCut() {}
	
	@After("loggingPointCut()")
	public void restCall(JoinPoint joinPoint) {
		logger.info("" + joinPoint.getSignature());
	}

}
