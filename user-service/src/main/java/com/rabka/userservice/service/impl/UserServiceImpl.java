package com.rabka.userservice.service.impl;

import com.rabka.userservice.dto.LoginDto;
import com.rabka.userservice.dto.RegisterDto;
import com.rabka.userservice.dto.UserResponseDto;
import com.rabka.userservice.dto.UserUpdateDto;
import com.rabka.userservice.entity.User;
import com.rabka.userservice.mapper.UserMapper;
import com.rabka.userservice.repository.UserRepository;
import com.rabka.userservice.security.JwtUtil;
import com.rabka.userservice.service.UserService;
import com.rabka.common.exception.AlreadyExistException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
//that is not completed yet
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Cacheable(value = "users", key = "#id")
    public UserResponseDto getUserById(Long id) {
        log.debug("getUserById started | id: {}", id);
        User user = userRepository.findById(id)
                // new exception needed
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + id));
        return userMapper.userToUserResponseDto(user);
    }

    @CacheEvict(value = "users", key = "#id")
    @Transactional
    public void updateUser(Long id, UserUpdateDto dto) {
        log.debug("updateUser started | id: {}", id);
        User user =  userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + id));
        if(dto.birthday() != null)
            user.setBirthday(dto.birthday());
        if(dto.password() != null)
            user.setPassword(passwordEncoder.encode(dto.password()));
        if(dto.email() != null)
            user.setEmail(dto.email());
        if(dto.name() != null)
            user.setName(dto.name());
    }

    @CacheEvict(value = "users", key = "#id")
    @Transactional
    public void deleteUser(Long id) {
        log.debug("Deleting user with id: {}", id);
        userRepository.deleteById(id);
        log.info("Deleted user with id: {}", id);
    }
    @Override
    public UserResponseDto login(LoginDto loginDto) {
        log.debug("Login started for email: {}", loginDto.email());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.email(), loginDto.password())
        );

        User user = userRepository.findByEmail(loginDto.email())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtUtil.generateToken(user);

        return UserResponseDto.builder()
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
