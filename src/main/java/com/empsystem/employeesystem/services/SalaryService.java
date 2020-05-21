package com.empsystem.employeesystem.services;

import com.empsystem.employeesystem.Exception.NotFoundException;
import com.empsystem.employeesystem.model.Salary;
import com.empsystem.employeesystem.repo.SalaryRepository;
import com.empsystem.employeesystem.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = "salarycache")
public class SalaryService {

    @Autowired
    SalaryRepository salaryRepository;

    @Autowired
    private UserRepository userRepository;

    @Cacheable
    public List<Salary> getAllSalaryInfo(){
        return salaryRepository.findAll();
    }

    @Cacheable
    public Optional<Salary> getSalaryInfo(Long empid){
        return salaryRepository.findById(empid);
    }

    public Salary addSalaryInfo (Long empid,Salary sal){
        return userRepository.findById(empid).map(users -> {
            sal.setUsers(users);
            return salaryRepository.saveAndFlush(sal);
        }).orElseThrow(() -> new NotFoundException("User not found"));

    }

    @Cacheable
    public Salary updateSalaryInfo(Long empid,
                                   Salary salaryUpdated) {
        return salaryRepository.findById(empid)
                .map(salary -> {
                    salary.setSal(salaryUpdated.getSal());
                    salary.setIncentive(salaryUpdated.getIncentive());
                    salary.setTaxdeduction(salaryUpdated.getTaxdeduction());
                    return salaryRepository.saveAndFlush(salary);
                }).orElseThrow(() -> new NotFoundException("Contact not found!"));
    }


}
