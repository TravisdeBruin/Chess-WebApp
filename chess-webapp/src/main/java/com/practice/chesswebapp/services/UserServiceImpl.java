package com.practice.chesswebapp.services;

import com.practice.chesswebapp.dtos.UserDto;
import com.practice.chesswebapp.mappers.UserMapper;
import com.practice.chesswebapp.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;
//    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto saveNewUser(UserDto userDto) {
        return userMapper.userToUserDto(userRepository
                .save(userMapper.userDtoToUser(userDto)));
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
}
