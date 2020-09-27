package com.empsystem.employeesystem.controller;

import com.empsystem.employeesystem.model.ProjectDetails;
import com.empsystem.employeesystem.repo.ProjectDetailsRepository;
import com.empsystem.employeesystem.services.ProjectDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("api/users/projectdetails")
public class ProjectDetailsController {

    @Autowired
    private ProjectDetailsService projectDetailsService;

    @Autowired
    private ProjectDetailsRepository projectDetailsRepository;

    @GetMapping
    public CompletableFuture<List<ProjectDetails>> getall (){
        return projectDetailsService.getAll();
    }

    @PostMapping
    public void addProject (@RequestBody ProjectDetails projectDetails){
        projectDetailsService.addProject(projectDetails);
    }

    @DeleteMapping
    public void deleteProjectDetails (@PathVariable Long projectid){
        projectDetailsRepository.deleteById(projectid);
    }


}
