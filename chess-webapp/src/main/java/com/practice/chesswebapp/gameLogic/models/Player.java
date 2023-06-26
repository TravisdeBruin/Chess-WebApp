package com.practice.chesswebapp.gameLogic.models;

import com.practice.chesswebapp.entities.User;
import com.practice.chesswebapp.enums.EColour;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Player {
    private User user;
    private EColour colour;
}
