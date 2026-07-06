package com.rabka.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record LoginDto(
        @NotBlank(message = "email can't be empty")
        @Email
        String email,

        @NotNull(message = "password can't be empty")
        @Size(min = 8)
        String password
) {
}
