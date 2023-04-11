package com.stackroute.RestAopDemo.controller;

import com.stackroute.RestAopDemo.model.Employee;
import com.stackroute.RestAopDemo.service.EmployeeDao;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employees")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeDao dao;

    @PostMapping()
    public ResponseEntity<Employee> save(@Valid @RequestBody Employee employee) {
        return  new ResponseEntity<>(dao.save(employee), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> findById(@PathVariable Integer id){
        return  new ResponseEntity<>(dao.findById(id),HttpStatus.OK);
    }

    @GetMapping("dept/{name}")
    public ResponseEntity<List<Employee>> findByDepartment(@PathVariable String name){
        return new ResponseEntity<>(dao.findByDepartment(name),HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handleValidationException(MethodArgumentNotValidException  ex){
        Map<String , String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)->{
            String fieldName = ((FieldError)error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName,errorMessage);
        });
    return errors;
    }
}
