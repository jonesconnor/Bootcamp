package com.stackroute.tokengenerator.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class UserProfile {

	@Id
	String mailid;
	
	String password;
	
	String username;
	String addr;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}



	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
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

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
