package com.stackroute.RestAopDemo.repository;

import com.stackroute.RestAopDemo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    @Query(
            value = "select * from employee e where e.department= :dept",nativeQuery = true
    )
    List<Employee> findByDepartment(@Param("dept") String name);
}
