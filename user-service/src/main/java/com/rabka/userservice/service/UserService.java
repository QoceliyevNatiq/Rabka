package com.rabka.userservice.service;

import com.rabka.userservice.dto.LoginDto;
import com.rabka.userservice.dto.RegisterDto;
import com.rabka.userservice.dto.UserResponse;

public interface UserService {
    UserResponse login(LoginDto loginDto);
    void register(RegisterDto registerDto);
}
