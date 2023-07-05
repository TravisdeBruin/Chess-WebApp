package com.practice.chesswebapp.gameLogic.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.practice.chesswebapp.gameLogic.interfaces.Piece;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@JsonSerialize
@JsonDeserialize
@AllArgsConstructor
@NoArgsConstructor
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
