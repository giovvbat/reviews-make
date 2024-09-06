package com.giovanna.reviewsmake.dto.user;

import jakarta.validation.constraints.NotBlank;

public record RedefineUserPasswordRecordDto(@NotBlank String credential, @NotBlank String password) {
}
