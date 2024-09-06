package com.giovanna.reviewsmake.dto.error;

import org.springframework.http.HttpStatus;

public record RestErrorRecordDto(HttpStatus status, String message) {
}
