package com.example.tasktracker.service;

import com.example.tasktracker.model.Status;
import com.example.tasktracker.model.Task;
import com.example.tasktracker.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
       return taskRepository.findAll();
    }

    public List<Task> findTaskByStatus (Status status){
        return taskRepository.findByTaskStatus(status);
    }

    public List<Task> findTaskByUserId (Long user){
        return taskRepository.findByUserId(user);
    }

    public Optional<Task> findTaskByName(String name){
        return taskRepository.findByNameContaining(name);
    }

    public Task updateTask(Long id, Task newTask){
        Optional<Task> existingTask = taskRepository.findById(id);
        if (existingTask.isPresent()){
            Task task = existingTask.get();
            task.setTask_id(newTask.getTask_id());
            task.setTaskStatus(newTask.getTaskStatus());
            task.setDescription(newTask.getDescription());
            task.setName(newTask.getName());
            task.setCompleted(newTask.isCompleted());
            task.setUser(newTask.getUser());
            taskRepository.save(task);
        }
        return null;
    }

    public boolean deleteTaskById(Long id){
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()){
            taskRepository.delete(task.get());
            return true;
        }
        return false;
    }

    public Optional<Task> getById(Long id){
        return taskRepository.findById(id);
    }

    public Task addTask(Task task){
        return taskRepository.save(task);
    }

}
