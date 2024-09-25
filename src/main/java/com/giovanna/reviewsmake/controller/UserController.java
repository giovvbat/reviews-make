package com.giovanna.reviewsmake.controller;

import com.giovanna.reviewsmake.dto.user.*;
import com.giovanna.reviewsmake.model.UserModel;
import com.giovanna.reviewsmake.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserModel> saveUser(@RequestBody @Valid UserRecordDto userRecordDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(userRecordDto));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseRecordDto> validateUser(@RequestBody @Valid LoginRequestRecordDto loginRequestRecordDto) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.validateUser(loginRequestRecordDto));
    }

    @GetMapping
    public ResponseEntity<List<UserModel>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> getUser(@PathVariable(value="id") UUID userId) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUser(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateUserResponseRecordDto> updateUser(@PathVariable(value="id") UUID userId, @RequestBody @Valid UserRecordDto userRecordDto) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(userId, userRecordDto));
    }

    /*user is logged, wants to change password*/
    @PutMapping("/update-password")
    public ResponseEntity<UserModel> updateUserPassword(@RequestBody @Valid UpdateUserPasswordRecordDto updateUserPasswordRecordDto) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUserPassword(updateUserPasswordRecordDto));
    }

    /*user forgot password, wants to change password to make log-in possible*/
    @PutMapping("/redefine-password")
    public ResponseEntity<UserModel> redefineUserPassword(@RequestBody @Valid RedefineUserPasswordRecordDto redefineUserPasswordRecordDto) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.redefineUserPassword(redefineUserPasswordRecordDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable(value="id") UUID userId) {
        userService.deleteUser(userId);

        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully");
    }
}