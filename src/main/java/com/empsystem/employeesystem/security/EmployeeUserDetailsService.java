/*package com.empsystem.employeesystem.security;

import com.empsystem.employeesystem.model.Login;
import com.empsystem.employeesystem.repo.LoginRepository;
import com.empsystem.employeesystem.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeUserDetailsService implements UserDetailsService {

    @Autowired
    LoginRepository loginRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Login> login = loginRepository.findByUsername(username);

        login.orElseThrow(() -> new UsernameNotFoundException("User Not Found" + username));

        return login.map(EmployeeUserDetails::new).get();
    }
}*/
