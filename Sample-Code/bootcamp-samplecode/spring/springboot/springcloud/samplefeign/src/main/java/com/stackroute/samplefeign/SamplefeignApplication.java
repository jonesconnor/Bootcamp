package com.stackroute.samplefeign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class SamplefeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(SamplefeignApplication.class, args);
	}

}
