package com.stackroute.spring.model;

import org.springframework.beans.factory.annotation.Autowired;

//Professor Model class
public class Professor {

    /*
     * This class should have three fields (employeeId, professorName, course).
     * Use @Autowired for Course field
     * This class should also contain the getters and setters for the fields
     */

	private String employeeId;
	private String professorName;
	@Autowired
	private Course course;

    //No-arg constructor
	
	public Professor() {}

    //Parameterized constructor to initialize employeeId and professorName
	
	public Professor(String employeeId, String professorName, Course course) {
		super();
		this.employeeId = employeeId;
		this.professorName = professorName;
		this.course = course;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getProfessorName() {
		return professorName;
	}

	public void setProfessorName(String professorName) {
		this.professorName = professorName;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	
	
	

}
