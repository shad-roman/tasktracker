package com.example.tasktracker.controller;
import java.util.List;

import com.example.tasktracker.model.Status;
import com.example.tasktracker.model.Task;
import com.example.tasktracker.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getById (@PathVariable Long id){
        return taskService.getById(id)
                .map(task -> ResponseEntity.ok(task))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Task> addNewTask(@RequestBody Task task){
        Task createdTask = taskService.addTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        if(taskService.deleteTaskById(id)){
            return  ResponseEntity.noContent().build();
        } else {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Task>> getByUserId(@PathVariable Long id){
        List<Task> tasks = taskService.findTaskByUserId(id);
        if (tasks.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Task> getByTaskName(@PathVariable String name){
        return taskService.findTaskByName(name)
                .map(task -> ResponseEntity.ok(task))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/status/{st}")
    public ResponseEntity<List<Task>> getByStatus(@PathVariable String st){
        Status status = Status.valueOf(st.toUpperCase());
        List<Task> tasks = taskService.findTaskByStatus(status);
        if (tasks.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
        return ResponseEntity.ok(tasks);
    }
}
