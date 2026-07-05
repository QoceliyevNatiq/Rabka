package com.Rabka.rabka.service;

import com.Rabka.rabka.dto.LoginDto;
import com.Rabka.rabka.dto.RegisterDto;
import com.Rabka.rabka.dto.UserResponse;

public interface UserService {
    UserResponse login(LoginDto loginDto);
    void register(RegisterDto registerDto);
}
