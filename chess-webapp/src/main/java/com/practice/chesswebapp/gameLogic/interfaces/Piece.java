package com.practice.chesswebapp.gameLogic.interfaces;

import com.practice.chesswebapp.enums.EColour;
import com.practice.chesswebapp.gameLogic.models.Board;
import com.practice.chesswebapp.gameLogic.models.Direction;
import com.practice.chesswebapp.gameLogic.models.Position;

public interface Piece {
    Direction[] getDirections(Board board, Position position);
    EColour getColor();
    boolean isMoved();
    void setMoved(boolean moved);
}
