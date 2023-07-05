package com.practice.chesswebapp.gameLogic.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
@JsonSerialize
@JsonDeserialize
public class Direction {
    private int x;
    private int y;
    private int limit;
}
