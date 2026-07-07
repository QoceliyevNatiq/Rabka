package com.rabka.userservice.dto;

import lombok.Builder;

@Builder
public record UserResponseDto(
        String token,
        String email
) {
}
