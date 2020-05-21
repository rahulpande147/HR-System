package com.empsystem.employeesystem.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Department implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empid;
    private String departmentname;
    private  String deptshortname;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "users_empid",  nullable = false)
    private Users users;

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Department() {
    }

    public Department(Long empid, String departmentname, String deptshortname, Users users) {
        this.empid = empid;
        this.departmentname = departmentname;
        this.deptshortname = deptshortname;
        this.users = users;
    }

    public Long getEmpid() {
        return empid;
    }

    public void setEmpid(Long empid) {
        this.empid = empid;
    }

    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }

    public String getDeptshortname() {
        return deptshortname;
    }

    public void setDeptshortname(String deptshortname) {
        this.deptshortname = deptshortname;
    }
}
