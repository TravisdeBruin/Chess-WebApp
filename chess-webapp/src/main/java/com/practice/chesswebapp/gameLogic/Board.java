package com.practice.chesswebapp.gameLogic;

import com.practice.chesswebapp.gameLogic.Pieces.*;

public class Board {
    Square[][] squares;

    public Board()
    {
        this.resetBoard();
    }

    public Square getSquare(int x, int y) throws Exception {

        if (x < 0 || x > 7 || y < 0 || y > 7) {
            throw new Exception("Index out of bound");
        }

        return squares[x][y];
    }

    public void resetBoard()
    {
        // initialize white pieces
        squares[0][0] = new Square(0, 0, new Rook(true));
        squares[0][1] = new Square(0, 1, new Knight(true));
        squares[0][2] = new Square(0, 2, new Bishop(true));
        squares[0][3] = new Square(0, 2, new King(true));
        squares[0][4] = new Square(0, 2, new Queen(true));
        squares[0][5] = new Square(0, 2, new Bishop(true));
        squares[0][6] = new Square(0, 2, new Knight(true));
        squares[0][7] = new Square(0, 2, new Rook(true));

        // initialize white pawns
        for (int i = 0; i < 8; i++) {
        squares[1][i] = new Square(1, 0, new Pawn(true));
        }

        // initialize black pieces
        squares[7][0] = new Square(7, 0, new Rook(false));
        squares[7][1] = new Square(7, 1, new Knight(false));
        squares[7][2] = new Square(7, 2, new Bishop(false));
        squares[7][3] = new Square(7, 2, new King(false));
        squares[7][4] = new Square(7, 2, new Queen(false));
        squares[7][5] = new Square(7, 2, new Bishop(false));
        squares[7][6] = new Square(7, 2, new Knight(false));
        squares[7][7] = new Square(7, 2, new Rook(false));

        // initialize black pawns
        for (int i = 0; i < 8; i++) {
            squares[6][0] = new Square(6, i, new Pawn(false));
        }

        // initialize remaining squares without any piece
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                squares[i][j] = new Square(i, j, null);
            }
        }
    }
}