package com.giovanna.projectsti.controller;

import com.giovanna.projectsti.dto.UserLoginRecordDto;
import com.giovanna.projectsti.dto.UserRecordDto;
import com.giovanna.projectsti.model.ProductModel;
import com.giovanna.projectsti.model.UserModel;
import com.giovanna.projectsti.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<Object> saveUser(@RequestBody @Valid UserRecordDto userRecordDto) {
        Optional<UserModel> user = userRepository.findById(userRecordDto.name());

        if (user.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already taken!");
        }

        var userModel = new UserModel();
        BeanUtils.copyProperties(userRecordDto, userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(userModel));
    }

    @PostMapping("/login")
    public ResponseEntity<Object> validateUser(@RequestBody @Valid UserLoginRecordDto userLoginRecordDto) {
        Optional<UserModel> user = userRepository.findById(userLoginRecordDto.name());

        if (user.isPresent()) {
            if (user.get().getPassword().equals(userLoginRecordDto.password())) {
                return ResponseEntity.status(HttpStatus.OK).body(user.get());
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized credentials!");
    }

    @GetMapping
    public ResponseEntity<Object> getAllUsers() {
        List<UserModel> users = userRepository.findAll();

        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No users found!");
        }

        return ResponseEntity.status(HttpStatus.OK).body(userRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUser(@PathVariable(value="id") String userId) {
        Optional<UserModel> user = userRepository.findById(userId);

        if(user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
        }

        return ResponseEntity.status(HttpStatus.OK).body(user.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable(value="id") String userId, @RequestBody @Valid UserRecordDto userRecordDto) {
        Optional<UserModel> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
        }

        BeanUtils.copyProperties(userRecordDto, user.get());
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.save(user.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable(value="id") String userId) {
        Optional<UserModel> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
        }

        userRepository.deleteById(userId);
        return ResponseEntity.status(HttpStatus.OK).body("User successfully deleted!");
    }
}
