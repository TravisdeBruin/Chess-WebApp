package com.practice.chesswebapp.gameLogic.models;

import com.practice.chesswebapp.gameLogic.interfaces.Piece;
import lombok.Data;

@Data
public class Row {
    Square[] squares;

    public Row(Piece[] pieces, int y) {
        this.squares = new Square[8];
        int x=0;
        for (Piece piece: pieces) {
            this.squares[x] = new Square(new Position(x,y), piece);
            x++;
        }
    }

    public Row(int y) {
        this.squares = new Square[8];
        for (int x=0; x<8; x++) {
            this.squares[x] = new Square(new Position(x,y), null);
        }
    }
}
