package com.empsystem.employeesystem.services;

import com.empsystem.employeesystem.Exception.NotFoundException;
import com.empsystem.employeesystem.model.ProjectDetails;
import com.empsystem.employeesystem.repo.ProjectDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@CacheConfig(cacheNames = "projectcache")
public class ProjectDetailsService {

    @Autowired
    private ProjectDetailsRepository projectDetailsRepository;

    @Cacheable
    public CompletableFuture<List<ProjectDetails>> getAll(){
        List<ProjectDetails> projectDetails= projectDetailsRepository.findAll();
        return CompletableFuture.completedFuture(projectDetails);
    }

    @Cacheable
    public void addProject (ProjectDetails projectDetails){
         projectDetailsRepository.saveAndFlush(projectDetails);
    }

    @Async
    @Cacheable
    public CompletableFuture<Optional<ProjectDetails>> getOneById(Long projectId) {
        Optional<ProjectDetails> projectDetails= projectDetailsRepository.findById(projectId);
        return CompletableFuture.completedFuture(projectDetails);
    }

    @Async
    @Cacheable
    public CompletableFuture<ProjectDetails> updateProjectDetails(Long projectid, ProjectDetails projectDetailsUpdated){
        return projectDetailsRepository.findById(projectid)
                 .map(projectDetails -> {
                    projectDetails.setEndDate(projectDetailsUpdated.getEndDate());
                    projectDetails.setNoOfEmployee(projectDetailsUpdated.getNoOfEmployee());
                    projectDetails.setProjectDescription(projectDetailsUpdated.getProjectDescription());
                    projectDetails.setProjectname(projectDetailsUpdated.getProjectname());
                    projectDetails.setStartDate(projectDetailsUpdated.getStartDate());
                    ProjectDetails projectDetails1= projectDetailsRepository.save(projectDetails);
                 return CompletableFuture.completedFuture(projectDetails1);
                 })
                .orElseThrow(() -> new NotFoundException("Project not found with Id-" +projectid));
    }

}
