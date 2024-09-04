package com.giovanna.reviewsmake.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateUserPasswordRecordDto(@NotBlank String currentPassword, @NotBlank String newPassword) {
}
