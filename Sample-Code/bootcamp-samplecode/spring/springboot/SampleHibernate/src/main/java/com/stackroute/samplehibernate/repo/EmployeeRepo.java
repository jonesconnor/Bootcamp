package com.stackroute.samplehibernate.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stackroute.samplehibernate.model.Employee;

@Repository
@Transactional
public class EmployeeRepo {
	
	@Autowired
	SessionFactory sessfactory;
	
	public String addEmployee(Employee emp)
	{
		sessfactory.getCurrentSession().save(emp);
		return "Saved";
	}
	
	public List<Employee> getEmployees()
	{
		return sessfactory.getCurrentSession().createQuery("from Employee").list();
	}

}
