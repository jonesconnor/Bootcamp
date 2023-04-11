package com.stackroute.RestAopDemo.service;

import com.stackroute.RestAopDemo.model.Employee;

import java.util.List;

public interface EmployeeDao {
    public Employee save(Employee employee);
    public Employee findById(Integer id);

    List<Employee> findByDepartment(String name);
}
