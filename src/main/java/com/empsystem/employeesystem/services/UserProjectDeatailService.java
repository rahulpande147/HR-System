package com.empsystem.employeesystem.services;

import com.empsystem.employeesystem.Exception.NotFoundException;
import com.empsystem.employeesystem.model.Users;
import com.empsystem.employeesystem.repo.ProjectDetailsRepository;
import com.empsystem.employeesystem.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProjectDeatailService {

    @Autowired
    private ProjectDetailsRepository projectDetailsRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Users> getProjectInfo(Long projectid){
        if(!projectDetailsRepository.existsById(projectid)){
            throw new NotFoundException("Project Not Found");
        }
        return userRepository.findByProjectDetailsProjectid(projectid);
    }


}
