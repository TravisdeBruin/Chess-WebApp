package com.practice.chesswebapp.gameLogic.Pieces;

import com.practice.chesswebapp.gameLogic.Board;
import com.practice.chesswebapp.gameLogic.Piece;
import com.practice.chesswebapp.gameLogic.Square;

public class Pawn extends Piece {
    public Pawn(boolean white) {
        super(white);
    }

    @Override
    public boolean canMove(Board board, Square start, Square end) {
        return false;
    }
}
