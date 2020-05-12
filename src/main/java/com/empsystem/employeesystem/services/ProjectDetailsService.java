package com.empsystem.employeesystem.services;

import com.empsystem.employeesystem.model.ProjectDetails;
import com.empsystem.employeesystem.repo.ProjectDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ProjectDetailsService {

    @Autowired
    private ProjectDetailsRepository projectDetailsRepository;


    public List<ProjectDetails> getAll(){
        return projectDetailsRepository.findAll();
    }

    public void addProject (ProjectDetails projectDetails){
         projectDetailsRepository.saveAndFlush(projectDetails);
    }

    public Optional<ProjectDetails> getOneById(Long projectId) {
        return projectDetailsRepository.findById(projectId);
    }


}
