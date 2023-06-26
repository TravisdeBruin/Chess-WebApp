package com.practice.chesswebapp.gameLogic.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Direction {
    private int x;
    private int y;
    private int limit;
}
