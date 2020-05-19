package com.empsystem.employeesystem.services;
import com.empsystem.employeesystem.model.Users;
import com.empsystem.employeesystem.repo.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<Users> getUserById(Long empid) {
        return userRepository.findById(empid);
    }

    public void addUser(Users users) {
        userRepository.saveAndFlush(users);
    }

    public Users updateUser(Long empid, Users users) {
        Users existingUser = userRepository.getOne(empid);
        BeanUtils.copyProperties(users, existingUser, "empid");
        return userRepository.saveAndFlush(existingUser);
    }

    /*public List<Users> getByFirstName(String fname) {
        return userRepository.findByFirstName(fname);
    }*/

    /*public Page<Users> getByWord(Optional<String> word,
                                 Optional<Integer> page, Optional<String> sortBy) {
        return userRepository.findByWord(word.orElse(" "),
                PageRequest.of(page.orElse(0), 5, Sort.Direction.ASC,sortBy.orElse("empid") ));
    }*/
}