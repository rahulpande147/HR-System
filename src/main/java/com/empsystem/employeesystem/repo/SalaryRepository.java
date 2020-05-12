package com.empsystem.employeesystem.repo;

import com.empsystem.employeesystem.model.Salary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaryRepository extends JpaRepository<Salary, Long> {

}
