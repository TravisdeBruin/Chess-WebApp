package com.practice.chesswebapp.gameLogic.Pieces;

import com.practice.chesswebapp.gameLogic.Board;
import com.practice.chesswebapp.gameLogic.Piece;
import com.practice.chesswebapp.gameLogic.Square;

public class King extends Piece {
    public King(boolean white) {
        super(white);
    }

    @Override
    public boolean canMove(Board board, Square start, Square end) {
        return false;
    }
}
