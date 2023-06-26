package com.practice.chesswebapp.gameLogic.webSocket.bean;

import com.practice.chesswebapp.gameLogic.models.Game;
import com.practice.chesswebapp.gameLogic.models.Movement;
import com.practice.chesswebapp.gameLogic.models.Player;

public class ReturnMessage {
    public Game game;
    public String type;
    public Player assignedPlayer;
    public Movement possibleMovements[];
    public String requestUUID;
}
