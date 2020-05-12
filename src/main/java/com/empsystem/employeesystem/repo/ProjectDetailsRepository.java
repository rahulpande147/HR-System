package com.empsystem.employeesystem.repo;

import com.empsystem.employeesystem.model.ProjectDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectDetailsRepository extends JpaRepository<ProjectDetails, Long> {
    List<ProjectDetails> findByProjectid(Long projectid);
}
