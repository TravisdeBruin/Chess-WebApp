package com.practice.chesswebapp.dtos;

import com.practice.chesswebapp.gameLogic.models.Chess;
import com.practice.chesswebapp.gameLogic.models.Move;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChessDto {
    private Chess chess;
//    private ChessGameDto chessGame;
    private Move move;
    private Long gameId;
    private boolean successFullMove;
}
