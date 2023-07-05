package com.practice.chesswebapp.services.interfaces;

import com.practice.chesswebapp.dtos.ChessDto;
import com.practice.chesswebapp.dtos.GameDto;
import com.practice.chesswebapp.entities.Game;
import com.practice.chesswebapp.gameLogic.models.Chess;
import org.springframework.stereotype.Service;

@Service
public interface GameService {
    GameDto createNewGame(GameDto game);
    GameDto connectToGame(GameDto gameDto);
    Chess startGame(Long gameId);
    Chess chess(ChessDto chessDto);
}
