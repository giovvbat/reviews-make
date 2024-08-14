package com.giovanna.projectsti.dto;

import jakarta.validation.constraints.NotBlank;

public record UserRecordDto(@NotBlank String name, @NotBlank String email, @NotBlank String password) {
}
