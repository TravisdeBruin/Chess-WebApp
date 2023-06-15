package com.practice.chesswebapp.gameLogic;

import com.practice.chesswebapp.enums.EColour;
import com.practice.chesswebapp.enums.EGameState;
import com.practice.chesswebapp.gameLogic.Pieces.King;
import com.practice.chesswebapp.gameLogic.Pieces.Rook;
import com.practice.chesswebapp.gameLogic.interfaces.Piece;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static com.practice.chesswebapp.enums.EGameState.*;
import static com.practice.chesswebapp.enums.EColour.*;

@Data
public class Game {
    private Board board;
    private Player whitePlayer;
    private Player blackPlayer;
    private String gameId;
    private EGameState gameState;
    private EColour turnColor;
    private boolean isPromotion;
    private Movement lastMovement;
    private Piece lastPieceAtPosition1;
    private Piece lastPieceAtPosition2;
    private LocalDateTime dateStarted;

    public Game(String uuid) {
        this.board = new Board();
        this.gameId = uuid;
        this.gameState = STARTED;
        this.turnColor = WHITE;
        this.isPromotion = false;
        this.dateStarted = LocalDateTime.now();
    }

    private EColour getEnemyColor(EColour color) {
        return color == WHITE ? BLACK : WHITE;
    }

    private void switchTurnColor() {
        this.turnColor = this.turnColor == WHITE ? BLACK :  WHITE;
    }

    public Player getPlayerById(Long id) {
        if (this.whitePlayer != null && this.whitePlayer.getUser().getId().equals(id)) {
            return whitePlayer;
        }else if (this.blackPlayer != null && this.blackPlayer.getUser().getId().equals(id)) {
            return blackPlayer;
        }else {
            return null;
        }
    }

    private void updateStatus() throws Exception {
        if (this.isOnCheckMate(WHITE) || this.isOnCheckMate(BLACK)) {
            this.gameState = CHECKMATE;
        } else if (this.isOnCheck(WHITE) || this.isOnCheck(BLACK)) {
            this.gameState = CHECK;
        } else {
            this.gameState = STARTED;
        }
    }

    private boolean isOnCheck(EColour eColour) {
        return false;
    }

    private boolean isOnCheckMate(EColour eColour) {
        return false;
    }

    public boolean movePiece(Movement movement, boolean updateStatus) throws Exception{
        Piece piece = this.board.getPieceAt(movement.getFromPosition());
        if (piece.getColor() != this.turnColor) {
            return false;
        }
        Movement possibleMovements[] = this.getAllPossibleMovements(movement.getFromPosition());
        for(Movement possibleMovement: possibleMovements) {
            EColour enemyColor = this.getEnemyColor(piece.getColor());
            if (movement.equals(possibleMovement) &&
                    !this.isOnCheckAfterMovement(movement) &&
                    this.gameState != CHECKMATE) {
                if (this.isCastling(movement)) {
                    this.doCastling(movement);
                } else {
                    this.board.setPieceAt(movement.getFromPosition(), null);
                    this.board.setPieceAt(movement.getToPosition(), piece);
                }
                Piece pieceAfterMove = this.board.getPieceAt(movement.getToPosition());
                pieceAfterMove.setMoved(true);
                this.switchTurnColor();
                this.isOnCheck(this.getEnemyColor(piece.getColor()));
                if (updateStatus) {
                    this.updateStatus();
                    this.updateIsPromotion(movement);
                }
                return true;
            }
        }
        return false;
    }

    public Movement[] getAllPossibleMovements(Position position) {
        ArrayList<Movement> movements = new ArrayList<Movement>();
        Piece piece = this.board.getPieceAt(position);
        if (piece != null) {
            Direction directions[] = piece.getDirections(this.board, position);
            for (Direction direction: directions) {
                Position positionFrom = position;
                Position positionTo = new Position(positionFrom.getX()+direction.getX(), positionFrom.getY()+direction.getY());
                int i = 0;
                int limit = direction.getLimit();
                while (this.canMoveTo(piece, positionTo) && i<limit) {
                    Piece pieceAtDestination = this.board.getPieceAt(positionTo);
                    if (pieceAtDestination != null && piece.getColor()!=pieceAtDestination.getColor()) {
                        limit = i;
                    }
                    movements.add(new Movement(position, positionTo));
                    positionFrom = positionTo;
                    positionTo = new Position(positionFrom.getX()+direction.getX(), positionFrom.getY()+direction.getY());
                    i++;
                }
            }
        }
        return movements.toArray(new Movement[0]);
    }

    private boolean canMoveTo(Piece piece, Position position) {
        if (!position.isWithinBoard()) {
            return false;
        }
        Piece pieceAtDestination = this.board.getPieceAt(position);
        if (pieceAtDestination == null ||
                piece.getColor() != pieceAtDestination.getColor() ||
                (piece.getClass().equals(King.class) &&
                        pieceAtDestination.getClass().equals(Rook.class) &&
                        !piece.isMoved() &&
                        !pieceAtDestination.isMoved())) {
            return true;
        } else {
            return false;
        }
    }
}
