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
        } else if (this.blackPlayer != null && this.blackPlayer.getUser().getId().equals(id)) {
            return blackPlayer;
        } else {
            return null;
        }
    }

    private void updateStatus() throws Exception {
        if (this.isInCheckMate(WHITE) || this.isInCheckMate(BLACK)) {
            this.gameState = CHECKMATE;
        } else if (this.isInCheck(WHITE) || this.isInCheck(BLACK)) {
            this.gameState = CHECK;
        } else {
            this.gameState = STARTED;
        }
    }

    private boolean isInCheck(EColour eColour) {
        return false;
    }

    private boolean isInCheckMate(EColour eColour) {
        return false;
    }

    public boolean movePiece(Movement move, boolean updateStatus) throws Exception {
        Piece piece = this.board.getPieceAt(move.getFromPosition());
        if (piece.getColor() != this.turnColor) {
            return false;
        }
        Movement possibleMoves[] = this.getAllPossibleMoves(move.getFromPosition());
        for (Movement possibleMove: possibleMoves) {
            EColour enemyColor = this.getEnemyColor(piece.getColor());
            if (move.equals(possibleMove) &&
                    !this.isInCheckAfterMovement(move) &&
                    this.gameState != CHECKMATE) {
                if (this.isCastling(move)) {
                    this.doCastling(move);
                } else {
                    this.board.setPieceAt(move.getFromPosition(), null);
                    this.board.setPieceAt(move.getToPosition(), piece);
                }
                Piece pieceAfterMove = this.board.getPieceAt(move.getToPosition());
                pieceAfterMove.setMoved(true);
                this.switchTurnColor();
                this.isInCheck(this.getEnemyColor(piece.getColor()));
                this.updateStatus();
                if (updateStatus) {
                    this.updateStatus();
                    this.updateIsPromotion(move);
                }
                return true;
            }
        }
        return false;
    }

    private void updateIsPromotion(Movement move) {
    }

    private void doCastling(Movement movement) {
    }

    private boolean isInCheckAfterMovement(Movement movement) {
        return false;
    }

    public Movement[] getAllPossibleMoves(Position position) {
        ArrayList<Movement> movements = new ArrayList<Movement>();
        Piece piece = this.board.getPieceAt(position);
        if (piece != null) {
            Direction directions[] = piece.getDirections(this.board, position);
            for (Direction direction: directions) {
                Position positionFrom = position;
                Position positionTo = new Position(positionFrom.getX() + direction.getX(), positionFrom.getY() + direction.getY());
                int i = 0;
                int limit = direction.getLimit();
                while (this.canMoveTo(piece, positionTo) && i < limit) {
                    Piece pieceAtDestination = this.board.getPieceAt(positionTo);
                    if (pieceAtDestination != null && piece.getColor() != pieceAtDestination.getColor()) {
                        limit = i;
                    }
                    movements.add(new Movement(position, positionTo));
                    positionFrom = positionTo;
                    positionTo = new Position(positionFrom.getX() + direction.getX(), positionFrom.getY() + direction.getY());
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

    private boolean isCastling(Movement movement) {
        if (this.board.getPieceAt(movement.getFromPosition()).getClass().equals(King.class) &&
                (movement.getFromPosition().getX() == movement.getToPosition().getX() - 2 ||
                        movement.getFromPosition().getX() == movement.getToPosition().getX() + 2)) {
            return true;
        }
        return false;
    }
}
