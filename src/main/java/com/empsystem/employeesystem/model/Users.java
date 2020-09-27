package com.empsystem.employeesystem.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

import java.util.Date;

@Entity (name = "Users")
public class Users implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long empid;

    private String username;
    private String fname;
    private String lname;

    @Enumerated(EnumType.STRING)
    private Gender gender;

        @Temporal(TemporalType.DATE)
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd-mm-yyyy" )
        private Date dob;

        private String email;

        private String contactno;

        private String city;

        private String address;

    /*@OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "users")*/
    @JoinColumn(referencedColumnName= "sal", insertable = true)
    @JsonIgnore
    private Salary salary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projectDetails_projectid")
    @JsonIgnore
    private ProjectDetails projectDetails;

    @JsonIgnore
    @OneToOne (fetch = FetchType.LAZY,cascade = CascadeType.ALL,
            mappedBy = "users")
    private Department department;

    /*@OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "users")
    */
    @OneToOne(mappedBy = "users", cascade = CascadeType.ALL)
    private Login login;

    public Users(Long empid, String username, String fname, String lname, Gender gender, Date dob,
                 String email, String contactno, String city, String address, Salary salary,
                 ProjectDetails projectDetails, Department department ,Login login) {
        this.empid = empid;
        this.username = username;
        this.fname = fname;
        this.lname = lname;
        this.gender = gender;
        this.dob = dob; 
        this.email = email;
        this.contactno = contactno;
        this.city = city;
        this.address = address;
        this.salary = salary;
        this.projectDetails = projectDetails;
        this.department = department;
        this.login = login;
    }

    public Users() {
    }

    public Long getEmpid() {
        return empid;
    }

    public void setEmpid(Long empid) {
        this.empid = empid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    public ProjectDetails getProjectDetails() {
        return projectDetails;
    }

    public void setProjectDetails(ProjectDetails projectDetails) {
        this.projectDetails = projectDetails;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }
}
