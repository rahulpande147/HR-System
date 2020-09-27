package com.empsystem.employeesystem.services;

import com.empsystem.employeesystem.Exception.NotFoundException;
import com.empsystem.employeesystem.model.Users;
import com.empsystem.employeesystem.repo.ProjectDetailsRepository;
import com.empsystem.employeesystem.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@CacheConfig(cacheNames = "userprojectcache")
public class UserProjectDeatailService {

    @Autowired
    private ProjectDetailsRepository projectDetailsRepository;

    @Autowired
    private UserRepository userRepository;

    @Cacheable
    public List<Users> getProjectInfo(Long projectid){
        if(!projectDetailsRepository.existsById(projectid)){
            throw new NotFoundException("Project Not Found");
        }
         return userRepository.findByProjectDetailsProjectid(projectid);
    }

    @Cacheable
    public Users addUserToProjectInfo ( Long projectid,  Users users ){
        return projectDetailsRepository.findById(projectid)
                .map(projectDetails -> {
                    users.setProjectDetails(projectDetails);
                    return userRepository.save(users);
                }).orElseThrow(() -> new NotFoundException("Project not found!"));
    }


}
