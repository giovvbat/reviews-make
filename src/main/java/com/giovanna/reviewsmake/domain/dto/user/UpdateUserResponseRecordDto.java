package com.giovanna.reviewsmake.domain.dto.user;

import com.giovanna.reviewsmake.domain.model.UserModel;

public record UpdateUserResponseRecordDto(UserModel user, String token) {
}
