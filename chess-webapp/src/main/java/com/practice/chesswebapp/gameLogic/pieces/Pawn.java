package com.practice.chesswebapp.gameLogic.pieces;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.practice.chesswebapp.enums.EColour;
import com.practice.chesswebapp.gameLogic.models.Board;
import com.practice.chesswebapp.gameLogic.models.Direction;
import com.practice.chesswebapp.gameLogic.models.Position;
import com.practice.chesswebapp.gameLogic.interfaces.Piece;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;

import static com.practice.chesswebapp.enums.EColour.BLACK;
import static com.practice.chesswebapp.enums.EColour.WHITE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize
@JsonDeserialize
@JsonTypeName("Pawn")
public class Pawn implements Piece {
    //    private String htmlCode;
    private EColour colour;
    private boolean moved;


    public Pawn(EColour colour) {
        this.colour = colour;

//        this.initHtmlCode();
    }

    @Override
    public Direction[] getDirections(Board board, Position position) {
        ArrayList<Direction> directions = new ArrayList<Direction>();
        int delta = 1;
        if (this.colour == BLACK) {
            delta = -1;
        }
        if (((position.getY() == 6 && this.colour == WHITE) ||
                (position.getY() == 1 && this.colour == BLACK)) &&
                board.getPieceAt(new Position(position.getX(), position.getY()-(2*delta))) == null &&
                board.getPieceAt(new Position(position.getX(), position.getY()-(1*delta))) == null) {
            directions.add(new Direction(0, -1*delta, 2));//move straight two squares
        }else if (board.getPieceAt(new Position(position.getX(), position.getY()-(1*delta))) == null) {
            directions.add(new Direction(0, -1*delta, 1));//move straight
        }
        Position positionTo = new Position(position.getX()-1, (position.getY()-(1*delta)));
        if (positionTo.isWithinBoard() && board.getPieceAt(positionTo) != null
                && board.getPieceAt(positionTo).getColour() != this.colour) {
            directions.add(new Direction(-1, -1*delta, 1));//move diagonal left when enemy is on the way
        }
        positionTo = new Position(position.getX()+1, (position.getY()-(1*delta)));
        if (positionTo.isWithinBoard() && board.getPieceAt(positionTo) != null
                && board.getPieceAt(positionTo).getColour() != this.colour) {
            directions.add(new Direction(1, -1*delta, 1));//move diagonal right when enemy is on the way
        }
        return directions.toArray(new Direction[0]);
    }
}
