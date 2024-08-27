package com.giovanna.reviewsmake.controller;

import com.giovanna.reviewsmake.dto.LoginRequestRecordDto;
import com.giovanna.reviewsmake.dto.LoginResponseRecordDto;
import com.giovanna.reviewsmake.dto.UserRecordDto;
import com.giovanna.reviewsmake.model.UserModel;
import com.giovanna.reviewsmake.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.giovanna.reviewsmake.security.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<Object> saveUser(@RequestBody @Valid UserRecordDto userRecordDto) {
        Optional<UserModel> userByUsername = userRepository.findByUsername(userRecordDto.username());
        Optional<UserModel> userByEmail = userRepository.findByEmail(userRecordDto.email());

        if (userByUsername.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already taken!");
        }

        if (userByEmail.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already taken!");
        }

        var userModel = new UserModel();
        BeanUtils.copyProperties(userRecordDto, userModel);
        userModel.setPassword(passwordEncoder.encode(userRecordDto.password()));

        return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(userModel));
    }

    @PostMapping("/login")
    public ResponseEntity<Object> validateUser(@RequestBody @Valid LoginRequestRecordDto loginRequestRecordDto) {
        Optional<UserModel> user = userRepository.findByUsername(loginRequestRecordDto.username());

        if (user.isPresent()) {
            if (passwordEncoder.matches(loginRequestRecordDto.password(), user.get().getPassword())) {
                String token = tokenService.generateToken(user.get());
                return ResponseEntity.status(HttpStatus.OK).body(new LoginResponseRecordDto(loginRequestRecordDto.username(), token));
                //return ResponseEntity.status(HttpStatus.OK).body(user.get());
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
    public ResponseEntity<Object> getUser(@PathVariable(value="id") UUID userId) {
        Optional<UserModel> user = userRepository.findById(userId);

        if(user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
        }

        return ResponseEntity.status(HttpStatus.OK).body(user.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable(value="id") UUID userId, @RequestBody @Valid UserRecordDto userRecordDto) {
        Optional<UserModel> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
        }

        BeanUtils.copyProperties(userRecordDto, user.get());
        user.get().setPassword(passwordEncoder.encode(userRecordDto.password()));
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.save(user.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable(value="id") UUID userId) {
        Optional<UserModel> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
        }

        userRepository.deleteById(userId);
        return ResponseEntity.status(HttpStatus.OK).body("User successfully deleted!");
    }
}
