package com.practice.chesswebapp.services;

import com.practice.chesswebapp.dtos.UserDto;
import com.practice.chesswebapp.entities.User;
import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);
    User findUserByUsername(String username);
    List<UserDto> findAllUsers();
}
