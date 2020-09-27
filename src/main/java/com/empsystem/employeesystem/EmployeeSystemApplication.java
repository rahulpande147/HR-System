package com.empsystem.employeesystem;

import com.empsystem.employeesystem.model.Salary;
import com.empsystem.employeesystem.model.Users;
import com.empsystem.employeesystem.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableCaching
// @EnableElasticsearchRepositories("com.empsystem.employeesystem.esrepo")
@SpringBootApplication
public class EmployeeSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeSystemApplication.class, args);

	}
}
