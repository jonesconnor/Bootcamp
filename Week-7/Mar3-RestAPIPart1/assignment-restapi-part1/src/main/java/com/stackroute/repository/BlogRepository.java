package com.stackroute.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.domain.Blog;

/* Add annotation to declare this class as a Repository class.
This interface should extend Jpa Repository
* */
@Repository
public interface BlogRepository extends JpaRepository<Blog,Integer>{
	
}
