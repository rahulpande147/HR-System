package com.empsystem.employeesystem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TempController {

    @GetMapping("/admin")
    public String getAdmin() {
        return ("<h1>Welcome Admin</h1>");
    }

    @GetMapping("/user")
    public String getUser() {
        return ("<h1>Welcome User</h1>");
    }
    @GetMapping("/")
    public String getUserAdmin() {
        return ("<h1>Welcome</h1>");
    }
}
