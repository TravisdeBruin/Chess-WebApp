package com.practice.chesswebapp.services.interfaces;

import com.practice.chesswebapp.dtos.GameDto;
import com.practice.chesswebapp.dtos.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface GameService {

    GameDto createNewGame(GameDto game);
    GameDto connectToGame(GameDto game);
    GameDto sow(Long gameId);

//    Optional<GameDto> getGameByGameId(UUID gameUUID);
//    List<UserDto> getAllGamesByUserId(Long id);
//    Boolean deleteByGameId(Long id);
//    Optional<GameDto> updateByGameId(Long id, UserDto userDto);
//    Boolean existsByGameUUID(UUID gameUUID);
}
