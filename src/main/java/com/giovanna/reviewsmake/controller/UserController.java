package com.giovanna.reviewsmake.controller;

import com.giovanna.reviewsmake.dto.LoginRequestRecordDto;
import com.giovanna.reviewsmake.dto.UserRecordDto;
import com.giovanna.reviewsmake.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Object> saveUser(@RequestBody @Valid UserRecordDto userRecordDto) {
        return userService.saveUser(userRecordDto);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> validateUser(@RequestBody @Valid LoginRequestRecordDto loginRequestRecordDto) {
        return userService.validateUser(loginRequestRecordDto);
    }

    @GetMapping
    public ResponseEntity<Object> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUser(@PathVariable(value="id") UUID userId) {
        return userService.getUser(userId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable(value="id") UUID userId, @RequestBody @Valid UserRecordDto userRecordDto) {
        return userService.updateUser(userId, userRecordDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable(value="id") UUID userId) {
        return userService.deleteUser(userId);
    }
}