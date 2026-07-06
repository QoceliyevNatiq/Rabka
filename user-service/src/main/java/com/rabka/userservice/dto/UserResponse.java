package com.rabka.userservice.dto;

import lombok.Builder;

@Builder
public record UserResponse(
        String token,
        String email
) {
}
