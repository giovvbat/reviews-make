package com.giovanna.reviewsmake.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestRecordDto(@NotBlank String username, @NotBlank String password) {
}
