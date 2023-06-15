package com.practice.chesswebapp.gameLogic;

import com.practice.chesswebapp.gameLogic.interfaces.Piece;
import lombok.*;

@Data
@AllArgsConstructor
public class Square {
     private Position position;
     private Piece piece;
}