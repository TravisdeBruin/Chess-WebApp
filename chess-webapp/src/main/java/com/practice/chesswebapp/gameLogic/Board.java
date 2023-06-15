package com.practice.chesswebapp.gameLogic;

import com.practice.chesswebapp.enums.EColour;
import com.practice.chesswebapp.gameLogic.Pieces.*;
import com.practice.chesswebapp.gameLogic.interfaces.Piece;

public class Board {

    private Row[] rows;
    public Board()
    {
        this.initBoard();
    }

    public void initBoard() {
        this.rows = new Row[8];
        this.rows[0] = new Row(new Piece[]{
                new Rook(EColour.BLACK),
                new Knight(EColour.BLACK),
                new Bishop(EColour.BLACK),
                new Queen(EColour.BLACK),
                new King(EColour.BLACK),
                new Bishop(EColour.BLACK),
                new Knight(EColour.BLACK),
                new Rook(EColour.BLACK)
        }, 0);
        this.rows[1] = new Row(new Piece[]{
                new Pawn(EColour.BLACK),
                new Pawn(EColour.BLACK),
                new Pawn(EColour.BLACK),
                new Pawn(EColour.BLACK),
                new Pawn(EColour.BLACK),
                new Pawn(EColour.BLACK),
                new Pawn(EColour.BLACK),
                new Pawn(EColour.BLACK)
        }, 1);
        for (int i=2; i<6; i++) {
            this.rows[i] = new Row(i);
        }
        this.rows[6] = new Row(new Piece[]{
                new Pawn(EColour.WHITE),
                new Pawn(EColour.WHITE),
                new Pawn(EColour.WHITE),
                new Pawn(EColour.WHITE),
                new Pawn(EColour.WHITE),
                new Pawn(EColour.WHITE),
                new Pawn(EColour.WHITE),
                new Pawn(EColour.WHITE)
        }, 6);
        this.rows[7] = new Row(new Piece[]{
                new Rook(EColour.WHITE),
                new Knight(EColour.WHITE),
                new Bishop(EColour.WHITE),
                new Queen(EColour.WHITE),
                new King(EColour.WHITE),
                new Bishop(EColour.WHITE),
                new Knight(EColour.WHITE),
                new Rook(EColour.WHITE)
        }, 7);
    }

    public Piece getPieceAt(Position position) {
        if (!position.isWithinBoard()) {
            return null;
        }
        return this.rows[position.getY()].getSquares()[position.getX()].getPiece();
    }

    public void setPieceAt(Position position, Piece piece) {
        this.rows[position.getY()].getSquares()[position.getX()].setPiece(piece);
    }

    public Position getKingPosition(EColour color) {
        for (int x=0; x<=7; x++) {
            for (int y=0; y<=7; y++) {
                Piece piece = this.rows[y].getSquares()[x].getPiece();
                if (piece != null && piece.getClass().equals(King.class) && piece.getColor() == color) {
                    return new Position(x, y);
                }
            }
        }
        return null;
    }

    public Row[] getRows() {
        return this.rows;
    }
}