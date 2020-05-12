package com.empsystem.employeesystem.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ProjectDetails")
public class ProjectDetails  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectid;
    private String projectname;
    private Long noOfEmployee;
    private Date startDate;
    private Date endDate;
    private String projectDescription;

    @OneToMany(mappedBy = "projectDetails", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Users> users;

    public Long getProjectid() {
        return projectid;
    }

    public void setProjectid(Long projectid) {
        this.projectid = projectid;
    }

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public Long getNoOfEmployee() {
        return noOfEmployee;
    }

    public void setNoOfEmployee(Long noOfEmployee) {
        this.noOfEmployee = noOfEmployee;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }
}
