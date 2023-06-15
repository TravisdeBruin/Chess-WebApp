package com.practice.chesswebapp.gameLogic.Pieces;

import com.practice.chesswebapp.enums.EColour;
import com.practice.chesswebapp.gameLogic.Board;
import com.practice.chesswebapp.gameLogic.Direction;
import com.practice.chesswebapp.gameLogic.Position;
import com.practice.chesswebapp.gameLogic.interfaces.Piece;
import lombok.Data;

import java.util.ArrayList;

@Data
public class King implements Piece {
    //    private String htmlCode;
    private EColour color;
    private boolean moved;

    public King(EColour color) {
        this.color = color;
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
//        directions.addAll(this.getCastlingDirectionsIfAllowed(board, position));
        return directions.toArray(new Direction[0]);
    }
}
