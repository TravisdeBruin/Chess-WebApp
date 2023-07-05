package com.practice.chesswebapp.services.interfaces;

import com.practice.chesswebapp.dtos.ChessDto;
import com.practice.chesswebapp.enums.EColour;
import com.practice.chesswebapp.gameLogic.interfaces.Piece;
import com.practice.chesswebapp.gameLogic.models.Chess;
import com.practice.chesswebapp.gameLogic.models.Move;
import com.practice.chesswebapp.gameLogic.models.Player;
import com.practice.chesswebapp.gameLogic.models.Position;
import org.springframework.stereotype.Service;

@Service
public interface ChessService {
    Chess chess(ChessDto chessDto);
    EColour getEnemyColour(EColour colour);
    void switchTurnColour(Chess chess);
    Player getPlayerById(Chess chess, Long id);
    void updateStatus(Chess chess);
    boolean movePiece(Chess chess, Move move, boolean updateStatus);
    void updateIsPromotion(Chess chess, Move move);
    void doCastling(Chess chess, Move move);
    boolean isInCheckAfterMove(Chess chess, Move move);
    Move[] getAllPossibleMoves(Chess chess, Position position);
    boolean canMoveTo(Chess chess, Piece piece, Position position);
    boolean isCastling(Chess chess, Move move);
    Position[] getAllPiecesPositions(Chess chess, EColour eColour);
    boolean isInCheck(Chess chess, EColour eColour);
    boolean isInCheckMate(Chess chess, EColour eColour);
    boolean pieceCanHitEnemyKing (Chess chess, Position position);
    void undoMove(Chess chess);
    void saveInfoForUndo(Chess chess, Move move);
    void doPromote(Chess chess, String pieceName);
    Piece getNewPieceByName(String name, EColour colour);
    Position getPawnToPromotePosition(Chess chess);
}
