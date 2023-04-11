package com.stackroute.javabasedapp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.stackroute.javabasedapp.config.UniversityConfig;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
       
    	ApplicationContext appcontext=new AnnotationConfigApplicationContext(UniversityConfig.class);
    
    	
    	Student student=appcontext.getBean("studentbean",Student.class);
     
    	System.out.println(student);
    	
    	
     
    	
    	
    	
    }
}
