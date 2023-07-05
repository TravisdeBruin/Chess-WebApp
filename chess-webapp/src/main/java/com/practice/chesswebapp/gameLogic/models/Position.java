package com.practice.chesswebapp.gameLogic.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize
@JsonDeserialize
public class Position {
    public int x;
    public int y;

    public boolean isWithinBoard() {
        return this.x>=0 && this.x<=7 && this.y>=0 && this.y<=7;
    }
}
