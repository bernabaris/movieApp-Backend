package com.github.bernabaris.movies.controllers;

import com.github.bernabaris.movies.models.User;
import com.github.bernabaris.movies.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody Map<String, String> payload) {
        User user = new User(payload.get("username"), payload.get("password"));
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody Map<String, String> payload) {
        User user = userService.findUserByUsername(payload.get("username"));
        Map<String, String> response = new HashMap<>();
        if (user != null && user.getPassword().equals(payload.get("password"))) {
            response.put("status", "success");
            response.put("message", "Login successful");
        } else {
            response.put("status", "error");
            response.put("message", "Invalid credentials");
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
