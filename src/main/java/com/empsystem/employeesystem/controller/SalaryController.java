package com.empsystem.employeesystem.controller;

import com.empsystem.employeesystem.Exception.NotFoundException;
import com.empsystem.employeesystem.model.Salary;
import com.empsystem.employeesystem.repo.SalaryRepository;
import com.empsystem.employeesystem.repo.UserRepository;
import com.empsystem.employeesystem.services.SalaryService;
import com.empsystem.employeesystem.specification.SalarySpecificationBuilder;
import com.empsystem.employeesystem.specification.SearchOperation;
import com.google.common.base.Joiner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public CompletableFuture<List<Salary>> get() {
        return salaryService.getAllSalaryInfo();
    }

    @GetMapping
    @RequestMapping("{id}")
    public CompletableFuture<Optional<Salary>> getById (@PathVariable Long empid){
        return salaryService.getSalaryInfo(empid);
    }

    @PostMapping("/{empid}")
    public CompletableFuture<Salary> addSalaryInfo (@PathVariable Long empid,
            /* @Valid*/ @RequestBody Salary sal) {
        return salaryService.addSalaryInfo(empid, sal);
    }

    @PutMapping({"empid"})
    public CompletableFuture<Salary> updateSalaryInfo(@PathVariable Long empid, @RequestBody Salary salaryUpdated){
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

    @GetMapping("/www")
    @ResponseBody
    public List<Salary> findAllBySpecification(@RequestParam(value = "search") String search){
        SalarySpecificationBuilder builder = new SalarySpecificationBuilder();
        String operationSet = Joiner.on("|").join(SearchOperation.SIMPLE_OPERATION_SET);
        Pattern pattern = Pattern.compile(
                "(\\w+?)(" + operationSet + ")(\\p{Punct}?)(\\w+?)(\\p{Punct}?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1),
                    matcher.group(2),
                    matcher.group(4),
                    matcher.group(3),
                    matcher.group(5));
        }
        Specification<Salary> spec = builder.build();
        return salaryRepository.findAll(spec);
    }
}
