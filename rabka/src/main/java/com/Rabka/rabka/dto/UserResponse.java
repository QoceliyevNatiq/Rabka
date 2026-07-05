package com.Rabka.rabka.dto;

import lombok.Builder;

@Builder
public record UserResponse(
        String token,
        String email
) {
}
