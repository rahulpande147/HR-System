package com.empsystem.employeesystem.services;

import com.empsystem.employeesystem.Exception.NotFoundException;
import com.empsystem.employeesystem.model.Department;
import com.empsystem.employeesystem.repo.DepartmentRepository;
import com.empsystem.employeesystem.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@CacheConfig(cacheNames = "departmentcache")
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private UserRepository userRepository;

    public CompletableFuture<List<Department>> getDepartment (){
        List<Department> dept = departmentRepository.findAll();
        return CompletableFuture.completedFuture(dept);
    }

    public CompletableFuture<Department> addDepartment (Long empid, Department department){
        return userRepository.findById(empid).map(users -> {
            department.setUsers(users);
            Department dept= departmentRepository.saveAndFlush(department);
            return CompletableFuture.completedFuture(dept);
        }).orElseThrow(() -> new NotFoundException("User not found"));
    }

    @Async
    @Cacheable
    public void deleteDepartment (Long departmentid){
       departmentRepository.deleteById(departmentid);
    }

    @Cacheable
    @Async
    public CompletableFuture<Department> updateDepartment (Long departmentid, Department departmentUpdated){
        return departmentRepository.findById(departmentid)
                .map(department -> {
                    department.setEmpid(departmentUpdated.getEmpid());
                    department.setDepartmentname(departmentUpdated.getDepartmentname());
                    department.setDeptshortname(departmentUpdated.getDeptshortname());
                    Department dept= departmentRepository.saveAndFlush(department);
                    return CompletableFuture.completedFuture(dept);
                }).orElseThrow(() -> new NotFoundException("Contact not found!"));
    }

}
