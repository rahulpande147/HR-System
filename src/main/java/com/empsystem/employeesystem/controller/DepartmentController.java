package com.empsystem.employeesystem.controller;

import com.empsystem.employeesystem.model.Department;
import com.empsystem.employeesystem.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("api/users/department")
public class DepartmentController implements Serializable {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public CompletableFuture<List<Department>> getAllDepartment (){
        return departmentService.getDepartment();
    }

    @PostMapping("/{empid}")
    public CompletableFuture<Department> addDepartment (@PathVariable Long empid, @RequestBody Department department){
        return departmentService.addDepartment(empid,department);
    }
}
