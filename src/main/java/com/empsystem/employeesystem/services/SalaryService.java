package com.empsystem.employeesystem.services;

import com.empsystem.employeesystem.Exception.NotFoundException;
import com.empsystem.employeesystem.model.Salary;
import com.empsystem.employeesystem.repo.SalaryRepository;
import com.empsystem.employeesystem.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@CacheConfig(cacheNames = "salarycache")
public class SalaryService {

    @Autowired
    SalaryRepository salaryRepository;

    @Autowired
    private UserRepository userRepository;

    @Cacheable
    @Async
    public CompletableFuture<List<Salary>> getAllSalaryInfo(){
        List<Salary> salary =salaryRepository.findAll();
        return CompletableFuture.completedFuture(salary);
    }

    @Cacheable
    @Async
    public CompletableFuture<Optional<Salary>> getSalaryInfo(Long empid){
        Optional<Salary> salary= salaryRepository.findById(empid);
        return CompletableFuture.completedFuture(salary);
    }

    @Cacheable
    @Async
    public CompletableFuture<Salary> addSalaryInfo (Long empid,Salary sal){
        return userRepository.findById(empid).map(users -> {
            sal.setUsers(users);
            Salary salary = salaryRepository.saveAndFlush(sal);
            return CompletableFuture.completedFuture(salary);
        }).orElseThrow(() -> new NotFoundException("User not found"));

    }

    @Cacheable
    @Async
    public CompletableFuture<Salary> updateSalaryInfo(Long empid,
                                   Salary salaryUpdated) {
        return salaryRepository.findById(empid)
                .map(salary -> {
                    salary.setSal(salaryUpdated.getSal());
                    salary.setIncentive(salaryUpdated.getIncentive());
                    salary.setTaxdeduction(salaryUpdated.getTaxdeduction());
                    Salary sal = salaryRepository.saveAndFlush(salary);
                    return CompletableFuture.completedFuture(sal);
                }).orElseThrow(() -> new NotFoundException("Contact not found!"));
    }


}
