package com.example.tasktracker.service;

import com.example.tasktracker.model.Task;
import com.example.tasktracker.model.Users;
import com.example.tasktracker.repository.TaskRepository;
import com.example.tasktracker.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public UsersService(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    public List<Users> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<Users> getUserById(Long id){
        return userRepository.findById(id);
    }

    public Users createUser(Users user){
        return userRepository.save(user);
    }

    public Users updateUser(Long id, Users updatedUser) {
        Optional<Users> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            Users user = existingUser.get();
            user.setDepartment(updatedUser.getDepartment());
            user.setUserName(updatedUser.getUserName());
            user.setUserId(updatedUser.getUserId());
            return userRepository.save(updatedUser);
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }

    @Transactional
    public boolean deleteUser(Long id) {
        Optional<Users> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            Users user = userOptional.get();

            List<Task> tasks = taskRepository.findByUserId(user.getUserId());

            for (Task temp : tasks){
                temp.setUser(null);
                taskRepository.save(temp);
            }
            userRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public List<Users> getUsersByDepartment(Integer dep){
        return userRepository.findUsersByDepartment(dep);
    }
}
