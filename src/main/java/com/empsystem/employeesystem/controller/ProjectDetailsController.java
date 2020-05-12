package com.empsystem.employeesystem.controller;

import com.empsystem.employeesystem.model.ProjectDetails;
import com.empsystem.employeesystem.model.Users;
import com.empsystem.employeesystem.repo.ProjectDetailsRepository;
import com.empsystem.employeesystem.services.ProjectDetailsService;
import com.empsystem.employeesystem.services.UserProjectDeatailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/projectdetails")
public class ProjectDetailsController {

    @Autowired
    private ProjectDetailsService projectDetailsService;

    @Autowired
    private ProjectDetailsRepository projectDetailsRepository;

    @GetMapping
    public List<ProjectDetails> getall (){
        return projectDetailsService.getAll();
    }

    @PostMapping
    public void addProject (@RequestBody ProjectDetails projectDetails){
        projectDetailsService.addProject(projectDetails);
    }



}
