package com.example.tasktracker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String fullName;

    private Integer department;

    public void setUserId(long userId) {
        this.id = userId;
    }


    public void setUserName(String userName) {
        this.fullName = userName;
    }

    public long getUserId() {
        return id;
    }

    public String getUserName() {
        return fullName;
    }

    public Integer getDepartment() {
        return department;
    }

    public void setDepartment(Integer department) {
        this.department = department;
    }
}
