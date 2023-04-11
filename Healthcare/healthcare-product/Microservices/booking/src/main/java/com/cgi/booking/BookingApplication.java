package com.cgi.booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.cgi.booking.filter.BookingFilter;

@SpringBootApplication
public class BookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingApplication.class, args);
	}
	
	@Bean
	public FilterRegistrationBean getFilter()
	{
		FilterRegistrationBean fbean=new FilterRegistrationBean();
		fbean.setFilter(new BookingFilter());
		fbean.addUrlPatterns("/bookings/update/*");
		return fbean;
	}


}
