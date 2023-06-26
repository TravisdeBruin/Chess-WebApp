package com.practice.chesswebapp.gameLogic.pieces;

import com.practice.chesswebapp.enums.EColour;
import com.practice.chesswebapp.gameLogic.models.Board;
import com.practice.chesswebapp.gameLogic.models.Direction;
import com.practice.chesswebapp.gameLogic.models.Position;
import com.practice.chesswebapp.gameLogic.interfaces.Piece;
import lombok.Data;

import java.util.ArrayList;

@Data
public class Knight implements Piece {
    //    private String htmlCode;
    private EColour color;
    private boolean moved;

    public Knight(EColour color) {
        this.color = color;
//        this.initHtmlCode();
    }

    @Override
    public Direction[] getDirections(Board board, Position position) {
        ArrayList<Direction> directions = new ArrayList<Direction>();
        directions.add(new Direction(-1, -2, 1));
        directions.add(new Direction(-2, -1, 1));
        directions.add(new Direction(-2,  1, 1));
        directions.add(new Direction(-1,  2, 1));
        directions.add(new Direction( 1,  2, 1));
        directions.add(new Direction( 2,  1, 1));
        directions.add(new Direction( 2, -1, 1));
        directions.add(new Direction( 1, -2, 1));
        return directions.toArray(new Direction[0]);
    }
}
