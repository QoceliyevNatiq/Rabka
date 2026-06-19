package com.Rabka.rabka.dto;

import jakarta.validation.constraints.*;
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
