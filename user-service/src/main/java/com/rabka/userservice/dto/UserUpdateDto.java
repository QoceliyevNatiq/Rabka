package com.rabka.userservice.dto;

import java.time.LocalDate;

public record UserUpdateDto(
        String name,
        String email,
        LocalDate birthday,
        String password
) {
}
