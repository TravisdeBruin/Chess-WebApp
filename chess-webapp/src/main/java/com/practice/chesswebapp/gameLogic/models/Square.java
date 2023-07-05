package com.practice.chesswebapp.gameLogic.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.practice.chesswebapp.gameLogic.interfaces.Piece;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize
@JsonDeserialize
public class Square {
     private Position position;
     private Piece piece;
}