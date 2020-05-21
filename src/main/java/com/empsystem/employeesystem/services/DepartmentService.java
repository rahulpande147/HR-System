package com.empsystem.employeesystem.services;

import com.empsystem.employeesystem.Exception.NotFoundException;
import com.empsystem.employeesystem.model.Department;
import com.empsystem.employeesystem.repo.DepartmentRepository;
import com.empsystem.employeesystem.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Department> getDepartment (){
        return departmentRepository.findAll();
    }

    public Department addDepartment (Long empid, Department department){
        return userRepository.findById(empid).map(users -> {
            department.setUsers(users);
            return departmentRepository.saveAndFlush(department);
        }).orElseThrow(() -> new NotFoundException("User not found"));
    }

    public void deleteDepartment (Long departmentid){
        departmentRepository.deleteById(departmentid);
    }

    public Department updateDepartment (Long departmentid, Department departmentUpdated){
        return departmentRepository.findById(departmentid)
                .map(department -> {
                    department.setEmpid(departmentUpdated.getEmpid());
                    department.setDepartmentname(departmentUpdated.getDepartmentname());
                    department.setDeptshortname(departmentUpdated.getDeptshortname());
                    return departmentRepository.saveAndFlush(department);
                }).orElseThrow(() -> new NotFoundException("Contact not found!"));
    }

}
