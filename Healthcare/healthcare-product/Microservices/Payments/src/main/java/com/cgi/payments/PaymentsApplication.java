package com.cgi.payments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.cgi.payments.filter.PaymentFilter;

import jakarta.servlet.GenericFilter;

@SpringBootApplication
public class PaymentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentsApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<?> getFilter() {
		FilterRegistrationBean<GenericFilter> fbean = new FilterRegistrationBean<GenericFilter>();
		fbean.setFilter(new PaymentFilter());
		fbean.addUrlPatterns("/api/v1/*");
		return fbean;
	}

}
