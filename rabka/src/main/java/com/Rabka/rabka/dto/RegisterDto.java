package com.Rabka.rabka.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record RegisterDto(
        @NotBlank(message = "ad bos ola bilmez")
        @Size(min = 3,max = 20)
        String name,

        @NotBlank(message = "email bos ola bilmez")
        @Email
        String email,

        @NotBlank(message = "password bos ola bilmez")
        @Size(max = 20)
        String password,

        @NotNull(message = "doqum tarixi bos ola bilmez")
        LocalDate birthday
) {
}
