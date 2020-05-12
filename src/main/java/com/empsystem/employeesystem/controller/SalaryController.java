package com.empsystem.employeesystem.controller;

import com.empsystem.employeesystem.Exception.NotFoundException;
import com.empsystem.employeesystem.model.Salary;
import com.empsystem.employeesystem.model.Users;
import com.empsystem.employeesystem.repo.SalaryRepository;
import com.empsystem.employeesystem.repo.UserRepository;
import com.empsystem.employeesystem.services.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/users/salary")
public class SalaryController implements Serializable {

    @Autowired
    SalaryService salaryService;

    @Autowired
    SalaryRepository salaryRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public List<Salary> get() {
        return salaryService.getAllSalaryInfo();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Optional<Salary> getById (@PathVariable Long empid){
        return salaryService.getSalaryInfo(empid);
    }

    @PostMapping("/{empid}")
    public Salary addSalaryInfo (@PathVariable Long empid,
            /* @Valid*/ @RequestBody Salary sal) {
        return salaryService.addSalaryInfo(empid, sal);
    }

    @PutMapping({"empid"})
    public Salary updateSalaryInfo(@PathVariable Long empid, @RequestBody Salary salaryUpdated){
        return salaryService.updateSalaryInfo(empid, salaryUpdated);
    }

    @DeleteMapping({"empid"})
    public String deleteSalaryInfo(@PathVariable Long empid) {
        return salaryRepository.findById(empid)
                .map(salary -> {
                    salaryRepository.delete(salary);
                    return "Deleted Successfully!";
                }).orElseThrow(() -> new NotFoundException("Salary not found!"));
    }
}
