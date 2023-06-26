package com.practice.chesswebapp.gameLogic.webSocket.bean;

import com.practice.chesswebapp.gameLogic.models.Movement;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Message {
    public String action;
    public Movement movement;
    public UUID gameUUID;
    public String playerUUID;
    public String requestUUID;
    public String promoteTo;
}
