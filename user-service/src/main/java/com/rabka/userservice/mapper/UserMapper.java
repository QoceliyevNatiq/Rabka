package com.rabka.userservice.mapper;

import com.rabka.userservice.dto.RegisterDto;
import com.rabka.userservice.dto.UserResponseDto;
import com.rabka.userservice.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    RegisterDto userToRegisterDto(User user);
    User registerDtoToUser(RegisterDto registerDto);
    UserResponseDto userToUserResponseDto(User user);
}
