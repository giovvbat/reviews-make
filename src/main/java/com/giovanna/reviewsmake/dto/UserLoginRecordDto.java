package com.giovanna.reviewsmake.dto;

import jakarta.validation.constraints.NotBlank;

public record UserLoginRecordDto(@NotBlank String name, @NotBlank String password) {
}
