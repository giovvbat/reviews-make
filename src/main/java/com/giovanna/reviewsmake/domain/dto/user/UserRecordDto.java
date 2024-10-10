package com.giovanna.reviewsmake.domain.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UserRecordDto(@NotBlank @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "username can not contains special characters") String username, @NotBlank @Email String email, @NotBlank String password) {
}
