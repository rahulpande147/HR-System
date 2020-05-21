package com.empsystem.employeesystem;

import com.empsystem.employeesystem.model.Salary;
import com.empsystem.employeesystem.model.Users;
import com.empsystem.employeesystem.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class EmployeeSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeSystemApplication.class, args);

	}


}
