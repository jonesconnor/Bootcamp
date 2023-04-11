package com.stackroute.spring.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Annotate the class with @Component so that spring framework will autodetect the class
 * for dependency injection
 */

@Component
public class Student {

    /**
     * This class should have three fields (studentId, studentName,courseList).
     * Use @Autowired annotation for courseList field
     * This class should also contain the getters and setters for the fields.
     */
	
	private String studentId;
	private String studentName;
	@Autowired
	private List<Course> courseList;

    //No-arg constructor
	
	public Student() {}

    //Parameterized constructor to initialize studentId and studentName
    
	public Student(String studentId, String studentName, List<Course> courseList) {
		super();
		this.studentId = studentId;
		this.studentName = studentName;
		this.courseList = courseList;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public List<Course> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}
	
	
}
