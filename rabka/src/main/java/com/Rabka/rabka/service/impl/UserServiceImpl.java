package com.Rabka.rabka.service.impl;

import com.Rabka.rabka.config.UserMapper;
import com.Rabka.rabka.dto.RegisterDto;
import com.Rabka.rabka.entity.users.User;
import com.Rabka.rabka.repo.UserRepository;
import com.Rabka.rabka.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    public void register(RegisterDto register) {
        log.debug("Register User started: {}", register);
        if(userRepository.findByEmail(register.email()).isPresent()){
            log.error("user is already exist {}",register.email());
            throw new RuntimeException();

        }
        User user = new User();
        userRepository.save(userMapper.registerDtoToUser(register));
//        user = userMapper.registerDtoToUser(register);

        log.debug("Register end");


    }
}
