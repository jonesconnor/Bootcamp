package com.stackroute.tokengenerator.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.tokengenerator.model.UserProfile;

@Repository
public interface UserRepo extends JpaRepository<UserProfile,String> {

	
UserProfile   findByMailidAndPassword(String mailid,String pwd);
}
