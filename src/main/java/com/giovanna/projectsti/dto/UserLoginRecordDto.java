package com.giovanna.projectsti.dto;

import jakarta.validation.constraints.NotBlank;

public record UserLoginRecordDto(@NotBlank String name, @NotBlank String password) {
}
