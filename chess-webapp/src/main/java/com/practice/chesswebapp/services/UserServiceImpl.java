package com.practice.chesswebapp.services;

import com.practice.chesswebapp.dtos.UserDto;
import com.practice.chesswebapp.entities.Role;
import com.practice.chesswebapp.entities.User;
import com.practice.chesswebapp.enums.ERole;
import com.practice.chesswebapp.mappers.UserMapper;
import com.practice.chesswebapp.repositories.RoleRepository;
import com.practice.chesswebapp.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private UserMapper userMapper;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto saveNewUser(UserDto userDto) {
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        User newUser = userMapper.userDtoToUser(userDto);
        newUser.setPassword(encodedPassword);

        Set<String> strRoles = userDto.getRole();
        List<Role> roles = new ArrayList<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        newUser.setRoles(roles);

        return userMapper.userToUserDto(userRepository
                .save(newUser));
    }

    @Override
    public Optional<UserDto> getUserByEmail(String email) {
        return Optional.ofNullable(userMapper.userToUserDto(userRepository.findByEmail(email).orElse(null)));
    }

    @Override
    public Optional<UserDto> getUserByUsername(String username) {
        return Optional.ofNullable(userMapper.userToUserDto(userRepository.findByUsername(username).orElse(null)));
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::userToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public Boolean deleteByUserId(Long id) {
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Optional<UserDto> updateByUserId(Long id, UserDto userDto) {
        AtomicReference<Optional<UserDto>> atomicReference = new AtomicReference<>();

        userRepository.findById(id).ifPresentOrElse(foundUser -> {
            foundUser.setName(userDto.getName());
            foundUser.setSurname(userDto.getSurname());
            atomicReference.set(Optional.of(userMapper
                    .userToUserDto(userRepository.save(foundUser))));
        }, () -> {
            atomicReference.set(Optional.empty());
        });

        return atomicReference.get();
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
