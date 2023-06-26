package com.practice.chesswebapp.mappers;

import com.practice.chesswebapp.dtos.GameDto;
import com.practice.chesswebapp.dtos.UserDto;
import com.practice.chesswebapp.entities.Game;
import com.practice.chesswebapp.entities.User;
import org.mapstruct.Mapper;

@Mapper
public interface GameMapper {
    Game gameDtoToGame(GameDto dto);
    GameDto gameToGameDto(Game user);
}