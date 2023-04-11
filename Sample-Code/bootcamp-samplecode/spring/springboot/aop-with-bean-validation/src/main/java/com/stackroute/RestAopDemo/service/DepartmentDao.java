package com.stackroute.RestAopDemo.service;

import com.stackroute.RestAopDemo.model.Department;

public interface DepartmentDao {

    public Department SaveDepartment(Department department);
    public Department findById(Integer id);
}
