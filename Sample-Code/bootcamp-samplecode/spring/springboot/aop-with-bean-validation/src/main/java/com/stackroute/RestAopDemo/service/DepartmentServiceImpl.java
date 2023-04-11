package com.stackroute.RestAopDemo.service;

import com.stackroute.RestAopDemo.model.Department;
import com.stackroute.RestAopDemo.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentDao{


    @Autowired
    private DepartmentRepository repository;

    @Override
    public Department SaveDepartment(Department department) {
        return repository.save(department);
    }

    @Override
    public Department findById(Integer id) {
        return repository.findById(id).orElse(null);
    }
}
