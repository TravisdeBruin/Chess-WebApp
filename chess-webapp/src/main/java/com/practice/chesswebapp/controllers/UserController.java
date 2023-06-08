package com.practice.chesswebapp.controllers;

import com.practice.chesswebapp.dtos.UserDto;
import com.practice.chesswebapp.exceptions.NotFoundException;
import com.practice.chesswebapp.responses.MessageResponse;
import com.practice.chesswebapp.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    public static final String USER_PATH = "api/v1/user";
    public static final String USER_PATH_ID = USER_PATH + "/{userId}";

    private final UserService userService;

    @DeleteMapping(USER_PATH_ID)
    public ResponseEntity deleteUserById(@PathVariable("userId") long userId) {

        if (!userService.deleteByUserId(userId)) {
            throw new NotFoundException();
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping(USER_PATH_ID)
    public ResponseEntity updateUserByID(@PathVariable("userId") long userId,
                                             @RequestBody UserDto userDto){

        if (userService.updateByUserId(userId, userDto).isEmpty()){
            throw new NotFoundException();
        }

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping(USER_PATH)
    public List<UserDto> getAllUser(){
        return userService.getAllUsers();
    }

    @GetMapping(value = USER_PATH + "/{userEmail}")
    public UserDto getUserByEmail(@PathVariable("userEmail") String userEmail){
        return userService.getUserByEmail(userEmail).orElseThrow(NotFoundException::new);
    }

    @GetMapping(value = USER_PATH + "/{username}")
    public UserDto getUserByUsername(@PathVariable("username") String username){
        return userService.getUserByUsername(username).orElseThrow(NotFoundException::new);
    }
}
