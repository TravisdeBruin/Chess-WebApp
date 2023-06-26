package com.practice.chesswebapp.gameLogic.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Movement {
    private Position fromPosition;
    private Position toPosition;
}
