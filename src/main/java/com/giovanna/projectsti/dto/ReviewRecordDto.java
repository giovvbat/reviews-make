package com.giovanna.projectsti.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ReviewRecordDto(@NotBlank String username, @NotNull UUID productId, @NotBlank String comment) {
}
