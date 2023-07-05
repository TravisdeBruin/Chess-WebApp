package com.practice.chesswebapp.gameLogic.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.practice.chesswebapp.entities.User;
import com.practice.chesswebapp.enums.EColour;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
@JsonSerialize
@JsonDeserialize
public class Player {
    private User user;
    private EColour colour;
}
