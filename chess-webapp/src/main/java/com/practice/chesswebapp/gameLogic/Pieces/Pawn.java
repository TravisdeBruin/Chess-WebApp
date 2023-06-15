package com.practice.chesswebapp.gameLogic.Pieces;

import com.practice.chesswebapp.enums.EColour;
import com.practice.chesswebapp.gameLogic.Board;
import com.practice.chesswebapp.gameLogic.Direction;
import com.practice.chesswebapp.gameLogic.Position;
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
