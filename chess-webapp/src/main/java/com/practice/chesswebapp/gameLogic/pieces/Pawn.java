package com.practice.chesswebapp.gameLogic.pieces;

import com.practice.chesswebapp.enums.EColour;
import com.practice.chesswebapp.gameLogic.models.Board;
import com.practice.chesswebapp.gameLogic.models.Direction;
import com.practice.chesswebapp.gameLogic.models.Position;
import com.practice.chesswebapp.gameLogic.interfaces.Piece;
import lombok.Data;

@Data
public class Pawn implements Piece {
    //    private String htmlCode;
    private EColour color;
    private boolean moved;

    public Pawn(EColour color) {
        this.color = color;
//        this.initHtmlCode();
    }

    @Override
    public Direction[] getDirections(Board board, Position position) {
        return new Direction[0];
    }
}
