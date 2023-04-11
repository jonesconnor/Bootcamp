package com.stackroute.tokengenerator.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.tokengenerator.model.UserProfile;

// include entity and any query in the repo

@Repository
public interface UserRepo extends MongoRepository<UserProfile,String> {

	UserProfile findByMailidAndPassword(String mailid,String pwd);
 
}
