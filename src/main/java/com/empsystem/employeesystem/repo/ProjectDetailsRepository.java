package com.empsystem.employeesystem.repo;

import com.empsystem.employeesystem.model.ProjectDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectDetailsRepository extends JpaRepository<ProjectDetails, Long> {
    List<ProjectDetails> findByProjectid(Long projectid);
}
