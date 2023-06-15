package com.practice.chesswebapp.gameLogic.interfaces;

import com.practice.chesswebapp.enums.EColour;
import com.practice.chesswebapp.gameLogic.Board;
import com.practice.chesswebapp.gameLogic.Direction;
import com.practice.chesswebapp.gameLogic.Position;

public interface Piece {
    Direction[] getDirections(Board board, Position position);
    EColour getColor();
    boolean isMoved();
    void setMoved(boolean moved);
}
