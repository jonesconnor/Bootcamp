package com.stackroute.newz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.stackroute.newz.fillter.NewsFilter;


/*
 * The @SpringBootApplication annotation is equivalent to using @Configuration, @EnableAutoConfiguration 
 * and @ComponentScan with their default attributes
 */

@SpringBootApplication
public class NewsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NewsServiceApplication.class, args);
	}
	
	@Bean
	public FilterRegistrationBean getFilter() {
		FilterRegistrationBean filterbean = new FilterRegistrationBean();
		filterbean.setFilter(new NewsFilter());
		filterbean.addUrlPatterns("/api/v1/news/");
		return filterbean;
	}

}
