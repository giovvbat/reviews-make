package com.giovanna.reviewsmake.service;

import com.giovanna.reviewsmake.domain.dto.user.*;
import com.giovanna.reviewsmake.infra.exception.user.*;
import com.giovanna.reviewsmake.domain.mapper.UserMapper;
import com.giovanna.reviewsmake.domain.model.UserModel;
import com.giovanna.reviewsmake.repository.ReviewRepository;
import com.giovanna.reviewsmake.repository.UserRepository;
import com.giovanna.reviewsmake.infra.security.TokenService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    TokenService tokenService;

    @Transactional
    public UserModel saveUser(UserRecordDto userRecordDto) {
        verifyAvailableUserCredential(new VerifyAvailableUserCredentialsRecordDto(userRecordDto.username(), userRecordDto.email()));

        UserModel userModel = UserMapper.instance.toEntity(userRecordDto);
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

    @Transactional
    public UpdateUserResponseRecordDto updateUser(UUID userId, UserRecordDto userRecordDto) {
        UserModel user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        verifyAvailableUserCredential(new VerifyAvailableUserCredentialsRecordDto(userRecordDto.username(), userRecordDto.email()));
        BeanUtils.copyProperties(userRecordDto, user);
        user.setPassword(passwordEncoder.encode(userRecordDto.password()));
        String token = tokenService.generateToken(user);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && Objects.equals(authentication.getName(), userRecordDto.username())) {
            Authentication newAuthentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(newAuthentication);
        }

        return new UpdateUserResponseRecordDto(userRepository.save(user), token);
    }

    @Transactional
    public UserModel redefineUserPassword(RedefineUserPasswordRecordDto redefineUserPasswordRecordDto) {
        Optional<UserModel> userByCredential = userRepository.findByUsername(redefineUserPasswordRecordDto.credential())
                .or(() -> userRepository.findByEmail(redefineUserPasswordRecordDto.credential()));

        return userByCredential.map(user -> {
            user.setPassword(passwordEncoder.encode(redefineUserPasswordRecordDto.password()));
            return userRepository.save(user);
        }).orElseThrow(() -> new UnauthorizedCredentialsException("unauthorized credential"));
    }

    @Transactional
    public UserModel updateUserPassword(UpdateUserPasswordRecordDto updateUserPasswordRecordDto) {
        Optional<UserModel> user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

        if (user.isEmpty()) {
            throw new UserNotFoundException("no user logged");
        }

        if (!passwordEncoder.matches(updateUserPasswordRecordDto.currentPassword(), user.get().getPassword())) {
            throw new UnauthorizedCredentialsException("wrong password provided");
        }

        if (passwordEncoder.matches(updateUserPasswordRecordDto.newPassword(), user.get().getPassword())) {
            throw new SamePasswordException();
        }

        user.get().setPassword(passwordEncoder.encode(updateUserPasswordRecordDto.newPassword()));

        return userRepository.save(user.get());
    }

    @Transactional
    public void deleteUser(UUID userId) {
        UserModel user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        reviewRepository.deleteByReviewUser(user);
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