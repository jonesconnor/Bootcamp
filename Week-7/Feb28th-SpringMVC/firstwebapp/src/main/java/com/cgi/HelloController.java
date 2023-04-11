package com.cgi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
	
	@RequestMapping("/add")
	public void displayData() {
		System.out.println("I am a handler method");
	}
}
