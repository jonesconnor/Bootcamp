package com.stackroute.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.stackroute.spring.config.AppConfig;
import com.stackroute.spring.model.Course;
import com.stackroute.spring.model.Professor;
import com.stackroute.spring.model.Student;

/**
 * This class is only for self testing whether beans are created by printing bean property values
 * Not a mandatory class to complete while submitting to hobbes
 */
public class App {
    public static void main(String[] args) {
        //Application Context to be created to access the spring beans

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        Student student = context.getBean("student", Student.class);
        Professor professor = context.getBean("professor", Professor.class);
        Course course = context.getBean("course", Course.class);

        System.out.println("Student ID: " + student.getStudentId());
        System.out.println("Student Name: " + student.getStudentName());
        System.out.println("Course ID: " + course.getCourseId());
        System.out.println("Course Name: " + course.getCourseName());
        System.out.println("Professor Name: " + professor.getProfessorName());
        System.out.println("Employee ID: " + professor.getEmployeeId());

    }
}
