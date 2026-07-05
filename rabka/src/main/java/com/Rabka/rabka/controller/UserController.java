package com.Rabka.rabka.controller;

import com.Rabka.rabka.dto.LoginDto;
import com.Rabka.rabka.dto.RegisterDto;
import com.Rabka.rabka.dto.UserResponse;
import com.Rabka.rabka.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/login")
    public UserResponse login(@RequestBody @Valid LoginDto loginDto) {
        return userService.login(loginDto);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody @Valid RegisterDto registerDto) {
        userService.register(registerDto);
    }
}
