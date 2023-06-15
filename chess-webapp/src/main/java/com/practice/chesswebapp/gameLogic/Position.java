package com.practice.chesswebapp.gameLogic;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Position {
    public int x;
    public int y;

    public boolean isWithinBoard() {
        return this.x>=0 && this.x<=7 && this.y>=0 && this.y<=7;
    }
}
