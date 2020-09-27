package com.empsystem.employeesystem.esservice;

import com.empsystem.employeesystem.esmodel.Employee;
import com.empsystem.employeesystem.esrepo.ElasticSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElasticSearchService {

    @Autowired
    public ElasticSearchRepository elasticSearchRepository;


    public Iterable<Employee> getAllEmployee() {
        return elasticSearchRepository.findAll();
    }

    public void createEmployee (Employee emp){
        elasticSearchRepository.save(emp);
    }

   /* public void saveEmployee(List<Employee> emp) {
        elasticSearchRepository.saveAll(emp);
    }*/
}
