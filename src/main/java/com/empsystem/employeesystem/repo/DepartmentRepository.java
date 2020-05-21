package com.empsystem.employeesystem.repo;

import com.empsystem.employeesystem.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
