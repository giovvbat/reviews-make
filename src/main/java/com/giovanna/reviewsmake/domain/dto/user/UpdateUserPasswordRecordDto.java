package com.giovanna.reviewsmake.domain.dto.user;

import jakarta.validation.constraints.NotBlank;

public record UpdateUserPasswordRecordDto(@NotBlank String currentPassword, @NotBlank String newPassword) {
}
