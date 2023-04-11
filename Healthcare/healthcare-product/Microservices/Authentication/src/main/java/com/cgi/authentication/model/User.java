package com.cgi.authentication.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {
	
	@Id
	String mailid;
	String password;
	String role;
	LocalDate createTime;
	
	public User () {
		createTime = LocalDate.now();
	}
	
	

	public User(String mailid, String password, String role) {
		this.mailid = mailid;
		this.password = password;
		this.role = role;
		createTime = LocalDate.now();
	}
	public void setCreateTime(LocalDate createTime) {
		this.createTime = createTime;
	}



	public LocalDate getCreateTime() {
		return createTime;
	}


	public String getMailid() {
		return mailid;
	}

	public void setMailid(String mailid) {
		this.mailid = mailid;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String pwd) {
		this.password = pwd;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}


	@Override
	public String toString() {
		return "User [mailid=" + mailid + ", password=" + password + ", role=" + role + ", createTime=" + createTime
				+ "]";
	}
	
	

	
}
