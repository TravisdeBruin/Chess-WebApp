package com.practice.chesswebapp.gameLogic;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
public class Square {
     private int x;
     private int y;
     private Piece piece;
}