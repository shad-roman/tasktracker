package com.example.tasktracker.controller;

import com.example.tasktracker.model.Task;
import com.example.tasktracker.model.Users;
import com.example.tasktracker.service.UsersService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UsersController {
    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public List<Users> getAllUsers(){
        return usersService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById (@PathVariable Long id){
        return usersService.getUserById(id)
                .map(users -> ResponseEntity.ok(users))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id){
        if (usersService.deleteUser(id)){
            return ResponseEntity.noContent().build();
        }  else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/dep/{number}")
    public ResponseEntity<List<Users>> getByUserId(@PathVariable Integer number){
        List<Users> tasks = usersService.getUsersByDepartment(number);
        if (tasks.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
        return ResponseEntity.ok(tasks);
    }

    @PostMapping
    public ResponseEntity<Users> addUser(@RequestBody Users users){
        Users user = usersService.createUser(users);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
