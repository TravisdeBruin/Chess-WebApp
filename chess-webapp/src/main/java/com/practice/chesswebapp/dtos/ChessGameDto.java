package com.practice.chesswebapp.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.practice.chesswebapp.enums.EColour;
import com.practice.chesswebapp.enums.EGameState;
import com.practice.chesswebapp.enums.EGameType;
import com.practice.chesswebapp.gameLogic.interfaces.Piece;
import com.practice.chesswebapp.gameLogic.models.Board;
import com.practice.chesswebapp.gameLogic.models.Move;
import com.practice.chesswebapp.gameLogic.models.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChessGameDto {
    public Board board;
    public Long gameId;
    public EGameState gameState;
    public EColour turnColour;
    public Move lastMove;
    public Piece lastPieceAtPosition1;
    public Piece lastPieceAtPosition2;
    public LocalDateTime dateStarted;
    public ArrayList<String> gameMoves;
    public ArrayList<String[][]> gameStates;
    public EGameType gameType;
    public boolean isPromotion;
}
