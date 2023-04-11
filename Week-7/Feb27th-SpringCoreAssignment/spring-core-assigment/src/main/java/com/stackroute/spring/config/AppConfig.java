package com.stackroute.spring.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.stackroute.spring.model.Course;
import com.stackroute.spring.model.Professor;
import com.stackroute.spring.model.Student;

/*This class will contain the application-context for the application.
 * Define the following annotations:
 * @Configuration - Annotating a class with the @Configuration indicates that the
 *                  class can be used by the Spring IoC container as a source of
 *                  bean definitions
 * @ComponentScan- this annotation is used to search for the Spring components amongst the application.
 *
 * */

@Configuration
@ComponentScan("com.stackroute.spring.model")
public class AppConfig {


    //Define a bean for courseList
	
	@Bean
    public List<Course> courseList() {
        List<Course> courses = new ArrayList<>();
        courses.add(course());
        return courses;
    }
	
    //Define a bean for student
	
 	@Bean
    public Student student() {
        Student student = new Student();
        student.setStudentId("S101");
        student.setStudentName("John");
        student.setCourseList(Collections.singletonList(course()));
        return student;
    }
 
 	//Define a bean for Course

    @Bean
    public Course course() {
        Course course = new Course();
        course.setCourseId("C101");
        course.setCourseName("Spring Boot");
        course.setStudentList(Collections.singletonList(student()));
        return course;
    }
    
    //Define a bean for professor
    
    @Bean
    public Professor professor() {
        Professor professor = new Professor();
        professor.setEmployeeId("E101");
        professor.setProfessorName("David");
        professor.setCourse(course());
        return professor;
    }

}
