package com.example.tasktracker.repository;

import com.example.tasktracker.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface UserRepository extends JpaRepository<Users, Long> {
    List<Users> findUsersByDepartment(Integer department);
}
