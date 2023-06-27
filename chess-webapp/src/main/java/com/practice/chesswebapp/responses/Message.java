package com.practice.chesswebapp.responses;

import com.practice.chesswebapp.enums.EMessageType;
import lombok.Data;

@Data
public class Message {
    private EMessageType type;
    private String message;
    private String room;

    public Message() {
    }

    public Message(EMessageType type, String message) {
        this.type = type;
        this.message = message;
    }
}
