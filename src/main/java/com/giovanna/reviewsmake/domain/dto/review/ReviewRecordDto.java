package com.giovanna.reviewsmake.domain.dto.review;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ReviewRecordDto(@NotNull UUID productId, @NotBlank String comment) {
}
