package com.cgi.medicalrecords;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.cgi.medicalrecords.filter.MedicalRecordFilter;

@SpringBootApplication
public class MedicalRecordsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicalRecordsApplication.class, args);
	}
	
	@Bean
	public FilterRegistrationBean getFilter() {
		FilterRegistrationBean fbean = new FilterRegistrationBean();
		fbean.setFilter(new MedicalRecordFilter());
		fbean.addUrlPatterns("/medicalrecords/*");
		return fbean;
	}

}
