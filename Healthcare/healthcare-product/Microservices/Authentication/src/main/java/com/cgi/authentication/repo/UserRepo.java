package com.cgi.authentication.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cgi.authentication.model.User;

@Repository
public interface UserRepo extends JpaRepository<User,String> {
	public User findByMailidAndPassword(String mailid,String pwd);
}
