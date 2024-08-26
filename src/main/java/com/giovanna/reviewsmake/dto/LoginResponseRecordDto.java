package com.giovanna.reviewsmake.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginResponseRecordDto(@NotBlank String username, @NotBlank String token) {
}
