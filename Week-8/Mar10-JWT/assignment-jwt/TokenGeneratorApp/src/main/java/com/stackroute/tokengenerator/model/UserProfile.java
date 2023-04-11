package com.stackroute.tokengenerator.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// below is an entity.mailid and password are the attributes for the below entity. mailId is primary key
@Document
public class UserProfile {

	@Id
	String mailId;
	String password;
	
	public String getMailId() {
		return mailId;
	}
	public void setMailId(String mailId) {
		this.mailId = mailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	 
}
