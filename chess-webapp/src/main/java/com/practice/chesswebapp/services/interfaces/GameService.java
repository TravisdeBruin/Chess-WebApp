package com.practice.chesswebapp.services;

import com.practice.chesswebapp.dtos.GameDto;
import com.practice.chesswebapp.dtos.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface GameService {

    GameDto saveNewGame(GameDto game);
    Optional<GameDto> getGameByGameId(UUID gameUUID);
    List<UserDto> getAllGamesByUserId(Long id);
    Boolean deleteByGameId(Long id);
    Optional<GameDto> updateByGameId(Long id, UserDto userDto);
    Boolean existsByGameUUID(UUID gameUUID);
}
