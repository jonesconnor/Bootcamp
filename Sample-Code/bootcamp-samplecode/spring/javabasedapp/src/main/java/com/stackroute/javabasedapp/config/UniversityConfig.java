package com.stackroute.javabasedapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;

import com.stackroute.javabasedapp.BeanCallBack;
import com.stackroute.javabasedapp.College;
import com.stackroute.javabasedapp.Student;

@Configuration
public class UniversityConfig {

	
//	@Bean("collegebean")
//	@Scope("prototype")
	//@Bean(initMethod= "afterBeanCall")
	//@Qualifer
	
	
	@Bean
	@Order(1)
	public College getCollege()
	{
		College college=new College();
		college.setCollegeName("ABC College");
		college.setAddr("Canada");
		return college;
	
	}
	
	@Bean
	public College getCollege2()
	{
		College college=new College();
		college.setCollegeName("Oxford");
		college.setAddr("England");
		return college;
	
	}
	
	@Bean
	public College getCollege3()
	{
		College college=new College();
		college.setCollegeName("Worldmission");
		college.setAddr("US");
		return college;
	
	}
	 @Bean("studentbean")
	//@Scope("prototype")

	public Student getStudent()
	{
		 Student student=new Student();
		 student.setRollno("S100");
		 student.setName("William");
		 return student;
		 
	}
	 
	 @Bean
	 public BeanCallBack getCallback()
	 {
		 return new BeanCallBack();
	 }
}
