package com.practice.chesswebapp.mappers;

import com.practice.chesswebapp.dtos.UserDto;
import com.practice.chesswebapp.entities.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    User userDtoToUser(UserDto dto);
    UserDto userToUserDto(User user);
}
