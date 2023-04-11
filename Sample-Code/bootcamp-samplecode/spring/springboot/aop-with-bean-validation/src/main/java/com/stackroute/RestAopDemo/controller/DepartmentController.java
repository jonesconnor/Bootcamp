package com.stackroute.RestAopDemo.controller;

import com.stackroute.RestAopDemo.model.Department;
import com.stackroute.RestAopDemo.service.DepartmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    @Autowired
    private DepartmentDao dao;

    @PostMapping()
    public ResponseEntity<Department> save(@RequestBody Department department) {
        return  new ResponseEntity<>(dao.SaveDepartment(department), HttpStatus.CREATED);
    }


}
