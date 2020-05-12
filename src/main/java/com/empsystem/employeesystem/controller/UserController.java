package com.empsystem.employeesystem.controller;

import com.empsystem.employeesystem.Exception.NotFoundException;
import com.empsystem.employeesystem.model.Users;
import com.empsystem.employeesystem.repo.UserRepository;
import com.empsystem.employeesystem.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
    @RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<Users> list(){
        return userService.getAllUsers();
    }

    @GetMapping
    @RequestMapping("{empid}")
    public Optional<Users> get(@PathVariable Long empid){
        return userService.getUserById(empid);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody final Users users){
        userService.addUser(users);
    }


    @RequestMapping(value = "{empid}", method = RequestMethod.PUT)
    public Users update(@PathVariable Long empid, @RequestBody Users users){
        return userService.updateUser(empid, users);
    }

    @DeleteMapping("{empid}")
    public String deleteStudent(@PathVariable Long empid) {
        return userRepository.findById(empid).map(users -> { userRepository.delete(users);
                return "Delete Successfully!";}).orElseThrow(() -> new
                        NotFoundException("Student not found with id " + empid));
    }
}
