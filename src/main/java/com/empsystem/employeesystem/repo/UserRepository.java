package com.empsystem.employeesystem.repo;

import com.empsystem.employeesystem.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;


public interface UserRepository extends JpaRepository <Users, Long>{
    List<Users> findByProjectDetailsProjectid(Long projectid);
}
