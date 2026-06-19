package com.Rabka.rabka.config;

import com.Rabka.rabka.dto.RegisterDto;
import com.Rabka.rabka.entity.users.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    RegisterDto userToRegisterDto(User user);
    User registerDtoToUser(RegisterDto registerDto);
}
