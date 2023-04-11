package com.stackroute.spring.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class Course {
    /**
     * This class should have three fields (courseId, courseName ).
     * Use @Autowired for studentList field
     * This class should also contain the getters and setters for the fields.
     */
	private String courseId;
	private String courseName;
	@Autowired
	private List<Student> studentList;

    //No-arg constructor
    public Course() {
    }

    //parameterized constructor to initialize courseId and courseName

    public Course(String courseId, String courseName, List<Student> studentList) {
		this.courseId = courseId;
		this.courseName = courseName;
		this.studentList = studentList;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public List<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}
    
    

}
