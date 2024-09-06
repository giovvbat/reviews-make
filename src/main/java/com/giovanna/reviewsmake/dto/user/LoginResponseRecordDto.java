package com.giovanna.reviewsmake.dto.user;

import jakarta.validation.constraints.NotBlank;

public record LoginResponseRecordDto(@NotBlank String username, @NotBlank String token) {
}
