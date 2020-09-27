package com.empsystem.employeesystem.model;

import javax.persistence.*;

@Entity
public class Login {

    @Id
    public Long id;
    public String username;
    public String role;
    public String password;
    public Boolean enabled;

    /*@OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "users_username", nullable = false)
    */
    @OneToOne
    @MapsId
    private Users users;

    public Login() {
    }

    public Login(Long id, String username, String role, String password, Boolean enabled) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.password = password;
        this.enabled = enabled;
    }

    public Login(Long id, String username, String role, String password, Boolean enabled, Users users) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.password = password;
        this.enabled = enabled;
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}


/*<!--Security-->
<!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-security -->
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-security</artifactId>
</dependency>
<dependency>
<groupId>org.springframework.security</groupId>
<artifactId>spring-security-test</artifactId>
<scope>test</scope>
</dependency>
<!--Security
<dependency>
<groupId>org.springframework.security</groupId>
<artifactId>spring-security-config</artifactId>
</dependency>-->*/