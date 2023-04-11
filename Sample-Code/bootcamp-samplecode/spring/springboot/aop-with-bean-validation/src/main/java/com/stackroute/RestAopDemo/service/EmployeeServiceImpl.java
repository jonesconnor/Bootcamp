package com.stackroute.RestAopDemo.service;

import com.stackroute.RestAopDemo.model.Employee;
import com.stackroute.RestAopDemo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeDao {

    @Autowired
    private EmployeeRepository repository;

    @Override
    public Employee save(Employee employee) {
        return repository.save(employee);
    }

    @Override
    public Employee findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Employee> findByDepartment(String name) {
        return repository.findByDepartment(name);
    }
}
