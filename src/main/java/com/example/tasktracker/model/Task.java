package com.example.tasktracker.model;

import jakarta.persistence.*;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long task_id;

    private String name;
    private boolean completed;
    private String description;
    private Status taskStatus;
    @ManyToOne
    @JoinColumn(name = "user_user_id")
    private Users user;

    public long getTask_id() {
        return task_id;
    }

    public boolean isCompleted() {
        return completed;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Status getTaskStatus() {
        return taskStatus;
    }

    public void setTask_id(long task_id) {
        this.task_id = task_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTaskStatus(Status taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
