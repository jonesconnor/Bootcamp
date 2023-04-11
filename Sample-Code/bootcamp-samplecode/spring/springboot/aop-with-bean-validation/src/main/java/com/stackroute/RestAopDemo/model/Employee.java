package com.stackroute.RestAopDemo.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data

public class Employee {
    @Id
    private int id;


    @NotBlank(message = "Name cannot be Blank")

    private String name;
    @NotBlank(message = "Department cannot be Blank")
    private String department;



}
