package com.practice.chesswebapp.gameLogic.models;

import com.practice.chesswebapp.gameLogic.interfaces.Piece;
import lombok.*;

@Data
@AllArgsConstructor
public class Square {
     private Position position;
     private Piece piece;
}