package com.giovanna.reviewsmake.domain.dto.user;

import jakarta.validation.constraints.NotBlank;

public record RedefineUserPasswordRecordDto(@NotBlank String credential, @NotBlank String password) {
}
