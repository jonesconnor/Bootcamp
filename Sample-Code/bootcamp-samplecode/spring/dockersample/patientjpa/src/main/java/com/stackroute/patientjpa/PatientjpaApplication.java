package com.stackroute.patientjpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.stackroute.patientjpa.filter.PatientFilter;

@SpringBootApplication
public class PatientjpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientjpaApplication.class, args);
	}
	
	
	@Bean
	public FilterRegistrationBean getFilter()
	{
		FilterRegistrationBean fbean=new FilterRegistrationBean();
		fbean.setFilter(new PatientFilter());
	fbean.addUrlPatterns("/patient/viewall");
		return fbean;
	}

}
