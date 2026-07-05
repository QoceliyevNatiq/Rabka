package com.Rabka.rabka.service.impl;

import com.Rabka.rabka.dto.LoginDto;
import com.Rabka.rabka.dto.RegisterDto;
import com.Rabka.rabka.dto.UserResponse;
import com.Rabka.rabka.entity.users.User;
import com.rabka.common.exception.AlreadyExistException;
import com.Rabka.rabka.mapstruct.UserMapper;
import com.Rabka.rabka.repo.UserRepository;
import com.Rabka.rabka.security.JwtUtil;
import com.Rabka.rabka.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Override
    public UserResponse login(LoginDto loginDto) {
        log.debug("Login started for email: {}", loginDto.email());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.email(), loginDto.password())
        );

        User user = userRepository.findByEmail(loginDto.email())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtUtil.generateToken(user);

        return UserResponse.builder()
                .token(token)
                .email(user.getEmail())
                .build();
    }

    @Override
    @Transactional
    public void register(RegisterDto register) {
        log.debug("Register User started: {}", register.email());

        if (userRepository.findByEmail(register.email()).isPresent()) {
            throw new AlreadyExistException("Bu email artiq movcuddur: " + register.email());
        }

        User user = userMapper.registerDtoToUser(register);
        user.setPassword(passwordEncoder.encode(register.password()));

        userRepository.save(user);
        log.info("User {} registered successfully", register.email());
    }
}
