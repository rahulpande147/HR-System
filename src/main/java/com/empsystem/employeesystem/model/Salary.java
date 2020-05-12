package com.empsystem.employeesystem.model;
import javax.persistence.*;
import java.io.Serializable;


@Entity
public class Salary implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long salaryid;
    private Long sal;
    private Long taxdeduction;
    private Long incentive;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "users_empid", nullable = false)
    private Users users;

    public Salary() {
    }

    public Long getSalaryid() {
        return salaryid;
    }

    public void setSalaryid(Long salaryid) {
        this.salaryid = salaryid;
    }

    public Long getSal() {
        return sal;
    }

    public void setSal(Long sal) {
        this.sal = sal;
    }

    public Long getTaxdeduction() {
        return taxdeduction;
    }

    public void setTaxdeduction(Long taxdeduction) {
        this.taxdeduction = taxdeduction;
    }

    public Long getIncentive() {
        return incentive;
    }

    public void setIncentive(Long incentive) {
        this.incentive = incentive;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
