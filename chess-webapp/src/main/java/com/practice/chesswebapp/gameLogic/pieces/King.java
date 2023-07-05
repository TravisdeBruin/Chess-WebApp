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

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize
@JsonDeserialize
@JsonTypeName("King")
public class King implements Piece {
    //    private String htmlCode;
    private EColour colour;
    private boolean moved;

    public King(EColour colour) {
        this.colour = colour;
//        this.initHtmlCode();
    }

    @Override
    public Direction[] getDirections(Board board, Position position) {
        ArrayList<Direction> directions = new ArrayList<Direction>();
        directions.add(new Direction( 0, -1, 1));
        directions.add(new Direction( 1,  0, 1));
        directions.add(new Direction( 0,  1, 1));
        directions.add(new Direction(-1,  0, 1));
        directions.add(new Direction(-1, -1, 1));
        directions.add(new Direction( 1, -1, 1));
        directions.add(new Direction(-1,  1, 1));
        directions.add(new Direction( 1,  1, 1));
        directions.addAll(this.getCastlingDirectionsIfAllowed(board, position));
        return directions.toArray(new Direction[0]);
    }

    private ArrayList<Direction> getCastlingDirectionsIfAllowed(Board board, Position position) {
        ArrayList<Direction> directions = new ArrayList<Direction>();
        Piece piece = board.getPieceAt(position);
        Piece pieceAtRookPositionLeft = board.getPieceAt(new Position(position.getY(), 0));
        Piece pieceAtRookPositionRight = board.getPieceAt(new Position(position.getY(), 7));
        if (pieceAtRookPositionRight != null &&
                pieceAtRookPositionRight.getClass().equals(Rook.class) &&
                ((Rook)pieceAtRookPositionRight).isMoved() == false &&
                ((King)piece).isMoved() == false &&
                board.getPieceAt(new Position(5, position.getY())) == null &&
                board.getPieceAt(new Position(6, position.getY())) == null ) {
            directions.add(new Direction(2, 0, 1));
        }
        if (pieceAtRookPositionLeft != null &&
                pieceAtRookPositionLeft.getClass().equals(Rook.class) &&
                ((Rook)pieceAtRookPositionLeft).isMoved() == false &&
                ((King)piece).isMoved() == false &&
                board.getPieceAt(new Position(3, position.getY())) == null &&
                board.getPieceAt(new Position(2, position.getY())) == null &&
                board.getPieceAt(new Position(1, position.getY())) == null) {
            directions.add(new Direction(-2, 0, 1));
        }
        return directions;
    }
}
