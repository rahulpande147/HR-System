package com.empsystem.employeesystem.services;

import com.empsystem.employeesystem.Exception.NotFoundException;
import com.empsystem.employeesystem.model.ProjectDetails;
import com.empsystem.employeesystem.repo.ProjectDetailsRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@CacheConfig(cacheNames = "projectcache")
public class ProjectDetailsService {

    @Autowired
    private ProjectDetailsRepository projectDetailsRepository;

    @Cacheable
    public List<ProjectDetails> getAll(){
        return projectDetailsRepository.findAll();
    }

    @Cacheable
    public void addProject (ProjectDetails projectDetails){
         projectDetailsRepository.saveAndFlush(projectDetails);
    }

    @Cacheable
    public Optional<ProjectDetails> getOneById(Long projectId) {
        return projectDetailsRepository.findById(projectId);
    }

    @Cacheable
    public ProjectDetails updateProjectDetails(Long projectid, ProjectDetails projectDetailsUpdated){
        return projectDetailsRepository.findById(projectid)
                 .map(projectDetails -> {
                    projectDetails.setEndDate(projectDetailsUpdated.getEndDate());
                    projectDetails.setNoOfEmployee(projectDetailsUpdated.getNoOfEmployee());
                    projectDetails.setProjectDescription(projectDetailsUpdated.getProjectDescription());
                    projectDetails.setProjectname(projectDetailsUpdated.getProjectname());
                    projectDetails.setStartDate(projectDetailsUpdated.getStartDate());
                return projectDetailsRepository.save(projectDetails);})
                .orElseThrow(() -> new NotFoundException("Project not found with Id-" +projectid));
    }

}
