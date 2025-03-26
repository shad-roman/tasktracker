package com.example.tasktracker.repository;

import com.example.tasktracker.model.Status;
import com.example.tasktracker.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByTaskStatus(Status status);
    Optional<Task> findByNameContaining(String name);
    List<Task> findByUserId(Long id);
}
