package com.stackroute.tokengenerator.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.stackroute.tokengenerator.model.UserProfile;

import jakarta.transaction.Transactional;

@Repository
public interface UserRepo extends JpaRepository<UserProfile,String> {

	
UserProfile   findByMailidAndPassword(String mailid,String pwd);


@Query("select obj from UserProfile obj where obj.username=?1")
UserProfile  getByName(String name);



@Modifying
@Transactional
@Query("delete from UserProfile u where u.username=?1")
int deleteUsingName(String name);

}
