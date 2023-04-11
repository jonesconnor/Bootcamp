package com.cgi.authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.cgi.authentication.filter.AuthFilter;

@SpringBootApplication
public class AuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationApplication.class, args);
	}
	
	@Bean
	public FilterRegistrationBean getFilter() {
		FilterRegistrationBean fbean = new FilterRegistrationBean();
		fbean.setFilter(new AuthFilter());
		fbean.addUrlPatterns("/auth/delete/*");
		return fbean;
	}


}
