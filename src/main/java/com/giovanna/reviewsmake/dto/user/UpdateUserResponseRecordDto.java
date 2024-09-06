package com.giovanna.reviewsmake.dto.user;

import com.giovanna.reviewsmake.model.UserModel;

public record UpdateUserResponseRecordDto(UserModel user, String token) {
}
