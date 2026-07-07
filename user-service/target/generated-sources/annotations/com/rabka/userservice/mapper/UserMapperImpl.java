package com.rabka.userservice.mapper;

import com.rabka.userservice.dto.RegisterDto;
import com.rabka.userservice.dto.UserResponseDto;
import com.rabka.userservice.entity.User;
import java.time.LocalDate;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-07T23:00:51+0400",
    comments = "version: 1.6.3, compiler: javac, environment: Java 26 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public RegisterDto userToRegisterDto(User user) {
        if ( user == null ) {
            return null;
        }

        String name = null;
        String email = null;
        String password = null;
        LocalDate birthday = null;

        name = user.getName();
        email = user.getEmail();
        password = user.getPassword();
        birthday = user.getBirthday();

        RegisterDto registerDto = new RegisterDto( name, email, password, birthday );

        return registerDto;
    }

    @Override
    public User registerDtoToUser(RegisterDto registerDto) {
        if ( registerDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.name( registerDto.name() );
        user.email( registerDto.email() );
        user.birthday( registerDto.birthday() );
        user.password( registerDto.password() );

        return user.build();
    }

    @Override
    public UserResponseDto userToUserResponseDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponseDto.UserResponseDtoBuilder userResponseDto = UserResponseDto.builder();

        userResponseDto.email( user.getEmail() );

        return userResponseDto.build();
    }
}
