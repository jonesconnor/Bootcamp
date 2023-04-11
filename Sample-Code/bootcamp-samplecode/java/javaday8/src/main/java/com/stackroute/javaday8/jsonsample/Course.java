package com.stackroute.javaday8.jsonsample;

public class Course {

	String courseName;
	String duration;
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	@Override
	public String toString() {
		return "Course [courseName=" + courseName + ", duration=" + duration + "]";
	}
	
}
