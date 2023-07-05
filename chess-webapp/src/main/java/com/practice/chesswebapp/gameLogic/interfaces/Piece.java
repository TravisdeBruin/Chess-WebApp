package com.practice.chesswebapp.gameLogic.interfaces;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.practice.chesswebapp.enums.EColour;
import com.practice.chesswebapp.gameLogic.models.Board;
import com.practice.chesswebapp.gameLogic.models.Direction;
import com.practice.chesswebapp.gameLogic.models.Position;
import com.practice.chesswebapp.gameLogic.pieces.*;

import java.io.Serializable;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Pawn.class, name = "Pawn"),
        @JsonSubTypes.Type(value = Rook.class, name = "Rook"),
        @JsonSubTypes.Type(value = Bishop.class, name = "Bishop"),
        @JsonSubTypes.Type(value = Knight.class, name = "Knight"),
        @JsonSubTypes.Type(value = Queen.class, name = "Queen"),
        @JsonSubTypes.Type(value = King.class, name = "King"),
})
public interface Piece {
    Direction[] getDirections(Board board, Position position);
    EColour getColour();
    boolean isMoved();
    void setMoved(boolean moved);
}
