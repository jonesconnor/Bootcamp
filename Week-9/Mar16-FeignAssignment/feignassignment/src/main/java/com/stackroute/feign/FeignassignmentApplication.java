package com.stackroute.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class FeignassignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeignassignmentApplication.class, args);
	}

}
