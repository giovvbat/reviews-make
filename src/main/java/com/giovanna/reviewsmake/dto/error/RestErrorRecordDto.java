package com.giovanna.reviewsmake.dto.error;

import jakarta.validation.constraints.NotBlank;

public record RestErrorRecordDto(@NotBlank String status, @NotBlank String message) {
}
