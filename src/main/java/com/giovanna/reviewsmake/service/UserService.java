package com.giovanna.reviewsmake.service;

import com.giovanna.reviewsmake.dto.*;
import com.giovanna.reviewsmake.exception.*;
import com.giovanna.reviewsmake.model.UserModel;
import com.giovanna.reviewsmake.repository.UserRepository;
import com.giovanna.reviewsmake.security.TokenService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    TokenService tokenService;

    public UserModel saveUser(UserRecordDto userRecordDto) {
        verifyAvailableUserCredential(new VerifyAvailableUserCredentialsRecordDto(userRecordDto.username(), userRecordDto.email()));

        var userModel = new UserModel();
        BeanUtils.copyProperties(userRecordDto, userModel);
        userModel.setPassword(passwordEncoder.encode(userRecordDto.password()));

        return userRepository.save(userModel);
    }

    public LoginResponseRecordDto validateUser(LoginRequestRecordDto loginRequestRecordDto) {
        Optional<UserModel> user = userRepository.findByUsername(loginRequestRecordDto.username());

        if (user.isPresent()) {
            if (passwordEncoder.matches(loginRequestRecordDto.password(), user.get().getPassword())) {
                String token = tokenService.generateToken(user.get());
                return new LoginResponseRecordDto(loginRequestRecordDto.username(), token);
            }
        }

        throw new UnauthorizedCredentialsException();
    }

    public List<UserModel> getAllUsers() {
        List<UserModel> users = userRepository.findAll();

        if (users.isEmpty()) {
            throw new NoUsersFoundException();
        }

        return userRepository.findAll();
    }

    public UserModel getUser(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
    }

    public UpdateUserResponseRecordDto updateUser(UUID userId, UserRecordDto userRecordDto) {
        UserModel user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        verifyAvailableUserCredential(new VerifyAvailableUserCredentialsRecordDto(userRecordDto.username(), userRecordDto.email()));
        BeanUtils.copyProperties(userRecordDto, user);
        user.setPassword(passwordEncoder.encode(userRecordDto.password()));
        String token = tokenService.generateToken(user);

        return new UpdateUserResponseRecordDto(userRepository.save(user), token);
    }

    public UserModel redefineUserPassword(RedefineUserPasswordRecordDto redefineUserPasswordRecordDto) {
        Optional<UserModel> userByCredential = userRepository.findByUsername(redefineUserPasswordRecordDto.credential())
                .or(() -> userRepository.findByEmail(redefineUserPasswordRecordDto.credential()));

        return userByCredential.map(user -> {
            user.setPassword(passwordEncoder.encode(redefineUserPasswordRecordDto.password()));
            return userRepository.save(user);
        }).orElseThrow(() -> new UnauthorizedCredentialsException("Unauthorized credential!"));
    }

    public UserModel updateUserPassword(UpdateUserPasswordRecordDto updateUserPasswordRecordDto) {
        UserModel user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(UserNotFoundException::new);

        if (!passwordEncoder.matches(updateUserPasswordRecordDto.currentPassword(), user.getPassword())) {
            throw new UnauthorizedCredentialsException("Wrong password provided!");
        }

        if (passwordEncoder.matches(updateUserPasswordRecordDto.newPassword(), user.getPassword())) {
            throw new SamePasswordException();
        }

        user.setPassword(passwordEncoder.encode(updateUserPasswordRecordDto.newPassword()));

        return userRepository.save(user);
    }

    public void deleteUser(UUID userId) {
        userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        userRepository.deleteById(userId);
    }

    public void verifyAvailableUserCredential(VerifyAvailableUserCredentialsRecordDto verifyAvailableUserCredentialsRecordDto) {
        if (userRepository.findByUsername(verifyAvailableUserCredentialsRecordDto.username()).isPresent()) {
            throw new UsernameAlreadyTakenException();
        }

        if (userRepository.findByEmail(verifyAvailableUserCredentialsRecordDto.email()).isPresent()) {
            throw new EmailAlreadyTakenException();
        }
    }
}