package com.stackroute.javabasedapp;

import org.springframework.beans.factory.config.BeanPostProcessor;

public class BeanCallBack implements BeanPostProcessor {

	public Object postProcessBeforeInitialization(Object bean, String beanname)
	{
		System.out.println("Bean Name in before initializer is " + beanname);
		
		return bean;
		
	}
	
	public Object postProcessAfterInitialization(Object bean,String beanname)
	{
		System.out.println(" bean name in afer initialzation is " + beanname);
		
//		if(bean instanceof Student)
//		{
//			Student student=(Student)bean;
//			student.setName("Elizabeth");
//			return student;
//		}
//		
		return bean;
		
 
	}
	
	
	
}
