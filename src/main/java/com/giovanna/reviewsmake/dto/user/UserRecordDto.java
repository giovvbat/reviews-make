package com.giovanna.reviewsmake.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRecordDto(@NotBlank String username, @NotBlank @Email String email, @NotBlank String password) {
}
