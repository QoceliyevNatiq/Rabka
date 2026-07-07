package com.rabka.userservice.service;

import com.rabka.userservice.dto.LoginDto;
import com.rabka.userservice.dto.RegisterDto;
import com.rabka.userservice.dto.UserResponseDto;

public interface UserService {
    UserResponseDto login(LoginDto loginDto);
    void register(RegisterDto registerDto);
}
