package com.stackroute.patientjpa.aopconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import  com.stackroute.patientjpa.aopconfig.AspectLogs;

@Configuration
@EnableAspectJAutoProxy
public class AopConfig {
	
	@Bean
	public AspectLogs  getAspect()
	{
		return new AspectLogs();
	}

}