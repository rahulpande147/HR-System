package com.empsystem.employeesystem.model;

import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ExecutionDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public String id;
    public Long startTime;
    public Long endtime;
    public String methodSignature;
    public Long excutionTimeInMillis;

    public ExecutionDetails() {
    }

    public ExecutionDetails(String id, Long startTime, Long endtime, String methodSignature, Long excutionTimeInMillis) {
        this.id = id;
        this.startTime = startTime;
        this.endtime = endtime;
        this.methodSignature = methodSignature;
        this.excutionTimeInMillis = excutionTimeInMillis;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndtime() {
        return endtime;
    }

    public void setEndtime(Long endtime) {
        this.endtime = endtime;
    }

    public Long getExcutionTimeInMillis() {
        return excutionTimeInMillis;
    }

    public void setExcutionTimeInMillis(Long excutionTimeInMillis) {
        this.excutionTimeInMillis = excutionTimeInMillis;
    }

    public String getMethodSignature() {
        return methodSignature;
    }

    public void setMethodSignature(String methodSignature) {
        this.methodSignature = methodSignature;
    }


}
