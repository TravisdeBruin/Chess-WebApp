package com.practice.chesswebapp.services.implementations;

import com.practice.chesswebapp.dtos.ChessDto;
import com.practice.chesswebapp.enums.EColour;
import com.practice.chesswebapp.gameLogic.interfaces.Piece;
import com.practice.chesswebapp.gameLogic.models.*;
import com.practice.chesswebapp.gameLogic.pieces.*;
import com.practice.chesswebapp.services.interfaces.ChessService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

import static com.practice.chesswebapp.enums.EColour.BLACK;
import static com.practice.chesswebapp.enums.EColour.WHITE;
import static com.practice.chesswebapp.enums.EGameState.*;

@Service
public class ChessServiceImpl implements ChessService {
    @Override
    public Chess chess(ChessDto chessDto) {
        Board board = chessDto.getChess().getBoard();




        chessDto.setSuccessFullMove(movePiece(chessDto.getChess(), chessDto.getMove(), true));
        return chessDto.getChess();
    }

    @Override
    public EColour getEnemyColour(EColour colour) {
        return colour == WHITE ? BLACK : WHITE;
    }

    @Override
    public void switchTurnColour(Chess chess) {
        chess.turnColour = chess.turnColour == WHITE ? BLACK :  WHITE;
    }

    @Override
    public Player getPlayerById(Chess chess, Long id) {
        if (chess.whitePlayer != null && chess.whitePlayer.getUser().getId().equals(id)) {
            return chess.whitePlayer;
        } else if (chess.blackPlayer != null && chess.blackPlayer.getUser().getId().equals(id)) {
            return chess.blackPlayer;
        } else {
            return null;
        }
    }

    @Override
    public void updateStatus(Chess chess) {
        if (isInCheckMate(chess, WHITE) || isInCheckMate(chess, BLACK)) {
            chess.gameState = CHECKMATE;
        } else if (isInCheck(chess, WHITE) || isInCheck(chess, BLACK)) {
            chess.gameState = CHECK;
        } else {
            chess.gameState = STARTED;
        }
    }

    @Override
    public boolean movePiece(Chess chess, Move move, boolean updateStatus) {
        Piece piece = chess.board.getPieceAt(move.getFromPosition());
        if (piece.getColour() != chess.turnColour) {
            return false;
        }
        Move[] possibleMoves = getAllPossibleMoves(chess, move.getFromPosition());
        for (Move possibleMove: possibleMoves) {
//            EColour enemyColour = getEnemyColour(piece.getColour());
            if (move.equals(possibleMove) &&
                    !isInCheckAfterMove(chess, move) &&
                    chess.gameState != CHECKMATE) {
                if (isCastling(chess, move)) {
                    doCastling(chess, move);
                } else {
                    chess.board.setPieceAt(move.getFromPosition(), null);
                    chess.board.setPieceAt(move.getToPosition(), piece);
                }
                Piece pieceAfterMove = chess.board.getPieceAt(move.getToPosition());
                pieceAfterMove.setMoved(true);
                switchTurnColour(chess);
                isInCheck(chess, getEnemyColour(piece.getColour()));
                if (updateStatus) {
                    updateStatus(chess);
                    updateIsPromotion(chess, move);
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public void updateIsPromotion(Chess chess, Move move) {
        Piece piece = chess.board.getPieceAt(move.getToPosition());
        if (piece == null) {
            chess.isPromotion = false;
            return;
        }
        if (Objects.equals(piece.getClass(), Pawn.class) &&
                piece.getColour() == WHITE &&
                move.getToPosition().getY() == 0) {
            switchTurnColour(chess);
            chess.isPromotion = true;
            return;
        } else if (Objects.equals(piece.getClass(), Pawn.class) &&
                piece.getColour() == BLACK &&
                move.getToPosition().getY() == 7) {
            switchTurnColour(chess);
            chess.isPromotion = true;
            return;
        }
        chess.isPromotion = false;
    }

    @Override
    public void doCastling(Chess chess, Move move) {
        int y = move.getToPosition().getY();
        if (move.getFromPosition().getX() == move.getToPosition().getX() + 2) {
            //castling left
            Rook rook = (Rook) chess.board.getPieceAt(new Position(0, y));
            chess.board.setPieceAt(new Position(0, y), null);
            King king = (King) chess.board.getPieceAt(move.getFromPosition());
            chess.board.setPieceAt(move.getFromPosition(), null);
            chess.board.setPieceAt(new Position(2, y), king);
            chess.board.setPieceAt(new Position(3, y), rook);
        }else if (move.getFromPosition().getX() == move.getToPosition().getX() - 2) {
            //castling right
            Rook rook = (Rook) chess.board.getPieceAt(new Position(7, y));
            chess.board.setPieceAt(new Position(7, y), null);
            King king = (King) chess.board.getPieceAt(move.getFromPosition());
            chess.board.setPieceAt(move.getFromPosition(), null);
            chess.board.setPieceAt(new Position(5, y), rook);
            chess.board.setPieceAt(new Position(6, y), king);
        }
    }

    @Override
    public boolean isInCheckAfterMove(Chess chess, Move move) {
        EColour colour = chess.board.getPieceAt(move.getFromPosition()).getColour();
        Piece pieceAtPosition1 = chess.board.getPieceAt(move.getFromPosition());
        Piece pieceAtPosition2 = chess.board.getPieceAt(move.getToPosition());
        chess.board.setPieceAt(move.getFromPosition(), null);
        chess.board.setPieceAt(move.getToPosition(), pieceAtPosition1);
        boolean isOnCheckAfterMove = isInCheck(chess, colour);
        chess.board.setPieceAt(move.getFromPosition(), pieceAtPosition1);
        chess.board.setPieceAt(move.getToPosition(), pieceAtPosition2);
        return isOnCheckAfterMove;
    }

    @Override
    public Move[] getAllPossibleMoves(Chess chess, Position position) {
        ArrayList<Move> moves = new ArrayList<>();
        Piece piece = chess.board.getPieceAt(position);
        if (piece != null) {
            Direction[] directions = piece.getDirections(chess.board, position);
            for (Direction direction: directions) {
                Position positionFrom = position;
                Position positionTo = new Position(positionFrom.getX() + direction.getX(), positionFrom.getY() + direction.getY());
                int i = 0;
                int limit = direction.getLimit();
                while (canMoveTo(chess, piece, positionTo) && i < limit) {
                    Piece pieceAtDestination = chess.board.getPieceAt(positionTo);
                    if (pieceAtDestination != null && piece.getColour() != pieceAtDestination.getColour()) {
                        limit = i;
                    }
                    moves.add(new Move(position, positionTo));
                    positionFrom = positionTo;
                    positionTo = new Position(positionFrom.getX() + direction.getX(), positionFrom.getY() + direction.getY());
                    i++;
                }
            }
        }
        return moves.toArray(new Move[0]);
    }

    @Override
    public boolean canMoveTo(Chess chess, Piece piece, Position position) {
        if (!position.isWithinBoard()) {
            return false;
        }
        Piece pieceAtDestination = chess.board.getPieceAt(position);
        return pieceAtDestination == null ||
                piece.getColour() != pieceAtDestination.getColour() ||
                (Objects.equals(piece.getClass(), King.class) &&
                        Objects.equals( pieceAtDestination.getClass(), Rook.class) &&
                        !piece.isMoved() &&
                        !pieceAtDestination.isMoved());
    }

    @Override
    public boolean isCastling(Chess chess, Move move) {
        return Objects.equals(chess.board.getPieceAt(move.getFromPosition()).getClass(), King.class) &&
                (move.getFromPosition().getX() == move.getToPosition().getX() - 2 ||
                        move.getFromPosition().getX() == move.getToPosition().getX() + 2);
    }


    @Override
    public Position[] getAllPiecesPositions(Chess chess, EColour eColour) {
        ArrayList<Position> positions = new ArrayList<>();
        for (int x=0; x<=7; x++) {
            for (int y=0; y<=7; y++) {
                Piece piece = chess.board.getPieceAt(new Position(x, y));
                if (piece != null && (eColour == null || piece.getColour() == eColour)) {
                    positions.add(new Position(x, y));
                }
            }
        }
        return positions.toArray(new Position[0]);
    }

    @Override
    public boolean isInCheck(Chess chess, EColour eColour) {
        //        Position kingPosition = game.board.getKingPosition(eColour);
        EColour enemyColour = getEnemyColour(eColour);
        Position[] allEnemyPositions = getAllPiecesPositions(chess, enemyColour);
        for (Position enemyPosition: allEnemyPositions) {
            if (pieceCanHitEnemyKing(chess, enemyPosition)){
                chess.gameState = CHECK;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isInCheckMate(Chess chess, EColour eColour) {
            Position[] allPositions = getAllPiecesPositions(chess, eColour);
        for (Position position: allPositions) {
            Move[] moves = getAllPossibleMoves(chess, position);
            for (Move move: moves) {
                saveInfoForUndo(chess, move);
                boolean moved = movePiece(chess, move,false);
                boolean isOnCheck = isInCheck(chess, eColour);
                if (moved) undoMove(chess);
                if (!isOnCheck) return false;
            }
        }
        chess.gameState = CHECKMATE;
        return true;
    }

    @Override
    public boolean pieceCanHitEnemyKing(Chess chess, Position position) {
        Piece piece = chess.board.getPieceAt(position);
        boolean pieceCanHitKing = false;
        Move[] possibleMoves = getAllPossibleMoves(chess, position);
        for (Move possibleMove: possibleMoves) {
            Piece targetPiece = chess.board.getPieceAt(possibleMove.getToPosition());
            if (targetPiece != null && Objects.equals(targetPiece.getClass(), King.class) &&
                    targetPiece.getColour() != piece.getColour()) {
                pieceCanHitKing = true;
            }
        }
        return pieceCanHitKing;
    }

    @Override
    public void undoMove(Chess chess) {
        chess.board.setPieceAt(chess.lastMove.getFromPosition(), chess.lastPieceAtPosition1);
        chess.board.setPieceAt(chess.lastMove.getToPosition(), chess.lastPieceAtPosition2);
        switchTurnColour(chess);
        chess.lastPieceAtPosition1.setMoved(false);
    }

    @Override
    public void saveInfoForUndo(Chess chess, Move move) {
        chess.lastMove = move;
        chess.lastPieceAtPosition1 = chess.board.getPieceAt(move.getFromPosition());
        chess.lastPieceAtPosition2 = chess.board.getPieceAt(move.getToPosition());
    }

    @Override
    public void doPromote(Chess chess, String pieceName) {
        Position pawnPosition = getPawnToPromotePosition(chess);
        Pawn pawn = (Pawn) chess.board.getPieceAt(pawnPosition);
        Piece piece = getNewPieceByName(pieceName, pawn.getColour());
        chess.isPromotion = false;
        switchTurnColour(chess);
        chess.board.setPieceAt(pawnPosition, piece);
        updateStatus(chess);
    }

    @Override
    public Piece getNewPieceByName(String name, EColour colour) {
        return switch (name) {
            case "Queen" -> new Queen(colour);
            case "Knight" -> new Knight(colour);
            case "Rook" -> new Rook(colour);
            case "Bishop" -> new Bishop(colour);
            default -> null;
        };
    }

    @Override
    public Position getPawnToPromotePosition(Chess chess) {
        Position[] positions = getAllPiecesPositions(chess, null);
        for (Position position: positions) {
            Piece piece = chess.board.getPieceAt(position);
            if (Objects.equals(piece.getClass(), Pawn.class) &&
                    piece.getColour() == WHITE &&
                    position.getY() == 0) {
                return position;
            } else if (Objects.equals(piece.getClass(), Pawn.class) &&
                    piece.getColour() == BLACK &&
                    position.getY() == 7) {
                return position;
            }
        }
        return null;
    }
}
