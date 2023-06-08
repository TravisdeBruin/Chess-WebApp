package com.practice.chesswebapp.services;

import com.practice.chesswebapp.dtos.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDto saveNewUser(UserDto userDto);
    Optional<UserDto> getUserByEmail(String email);
    Optional<UserDto> getUserByUsername(String username);
    List<UserDto> getAllUsers();
    Boolean deleteByUserId(Long id);
    Optional<UserDto> updateByUserId(Long id, UserDto userDto);
}
