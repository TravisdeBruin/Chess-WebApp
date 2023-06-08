package com.practice.chesswebapp.services;

import com.practice.chesswebapp.dtos.UserDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    UserDto saveNewUser(UserDto userDto);
    Optional<UserDto> getUserByEmail(String email);
    Optional<UserDto> getUserByUsername(String username);
    List<UserDto> getAllUsers();
    Boolean deleteByUserId(Long id);
    Optional<UserDto> updateByUserId(Long id, UserDto userDto);

    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
