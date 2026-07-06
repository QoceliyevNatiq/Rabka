package com.rabka.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record RegisterDto(
        @NotBlank(message = "ad bos ola bilmez")
        @Size(min = 3, max = 20)
        String name,

        @NotBlank(message = "email bos ola bilmez")
        @Email
        String email,

        @NotBlank(message = "password bos ola bilmez")
        @Size(min = 8, max = 20)
        String password,

        @NotNull(message = "doqum tarixi bos ola bilmez")
        LocalDate birthday
) {
}
