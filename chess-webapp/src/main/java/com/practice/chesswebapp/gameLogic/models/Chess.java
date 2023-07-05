package com.practice.chesswebapp.gameLogic.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.practice.chesswebapp.entities.Game;
import com.practice.chesswebapp.enums.EColour;
import com.practice.chesswebapp.enums.EGameState;
import com.practice.chesswebapp.enums.EGameType;
import com.practice.chesswebapp.gameLogic.interfaces.Piece;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static com.practice.chesswebapp.enums.EGameState.*;
import static com.practice.chesswebapp.enums.EColour.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize
@JsonDeserialize
public class Chess {
    public Board board;
    @JsonIgnore
    public Player whitePlayer;
    @JsonIgnore
    public Player blackPlayer;
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

    public Chess(Game game) {
        this.board = new Board();
        this.gameId = game.getId();
        this.gameState = game.getStatus();
        this.turnColour = WHITE;
        this.dateStarted = LocalDateTime.now();
        this.whitePlayer = new Player(game.getWhitePlayer(), WHITE);
        this.blackPlayer = new Player(game.getBlackPlayer(), BLACK);
        this.gameType = game.getGameType();
    }
}