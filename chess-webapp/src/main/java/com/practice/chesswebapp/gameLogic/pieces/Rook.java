package com.practice.chesswebapp.gameLogic.pieces;

import com.practice.chesswebapp.enums.EColour;
import com.practice.chesswebapp.gameLogic.models.Board;
import com.practice.chesswebapp.gameLogic.models.Direction;
import com.practice.chesswebapp.gameLogic.models.Position;
import com.practice.chesswebapp.gameLogic.interfaces.Piece;
import lombok.Data;

import java.util.ArrayList;

@Data
public class Rook implements Piece {
    //    private String htmlCode;
    private EColour color;
    private boolean moved;

    public Rook(EColour color) {
        this.color = color;
//        this.initHtmlCode();
    }

    @Override
    public Direction[] getDirections(Board board, Position position) {
        ArrayList<Direction> directions = new ArrayList<Direction>();
        directions.add(new Direction( 0, -1, 7));
        directions.add(new Direction( 1,  0, 7));
        directions.add(new Direction( 0,  1, 7));
        directions.add(new Direction(-1,  0, 7));
        return directions.toArray(new Direction[0]);
    }
}
