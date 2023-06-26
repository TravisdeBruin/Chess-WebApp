package com.practice.chesswebapp.gameLogic;

import com.google.gson.Gson;
import com.practice.chesswebapp.gameLogic.models.Game;
import com.practice.chesswebapp.gameLogic.models.Player;
import com.practice.chesswebapp.gameLogic.webSocket.GameController;
import com.practice.chesswebapp.gameLogic.webSocket.bean.Message;
import com.practice.chesswebapp.gameLogic.webSocket.bean.ReturnMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@Component
public class GameHandler extends TextWebSocketHandler {
    private static ArrayList<WebSocketSession> sessions;
    private static HashMap<Long, ArrayList<String>> webSocketSessionIdsByGame;
    private static GameController gameController;
    private static Gson gson;
    private static final String NEW_GAME = "newGame";
    private static final String JOIN_GAME = "joinGame";
    private static final Logger logger = LoggerFactory.getLogger(GameHandler.class);

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        if (gameController == null) {
            gameController = new GameController();
        }
        if (sessions == null) {
            sessions = new ArrayList<>();
        }
        if (gson == null) {
            gson = new Gson();
        }
        this.addSession(session);
        if (webSocketSessionIdsByGame == null) {
            webSocketSessionIdsByGame = new HashMap<>();
        }
    }
    private void addSession(WebSocketSession newSession) {
        boolean exists = false;
        for (WebSocketSession session: sessions) {
            if (session.getId().equals(newSession.getId())) {
                exists = true;
            }
        }
        if (!exists) {
            sessions.add(newSession);
        }
    }

    private void sendMessage(Long gameId, TextMessage message) throws IOException {
        ArrayList<String> webSocketSessionIds = getWebSocketSessionIdsByGame(gameId);
        for (WebSocketSession session: sessions) {
            for (String id: webSocketSessionIds) {
                try {
                    if (session.getId().equals(id)) {
                        System.out.println("sending message to: "+id);
                        session.sendMessage(message);
                    }
                } catch (Exception e) {
                    logger.error("EXCEPTION OCCURRED");
                    logger.error( "failed! ", e );
                }
            }
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws Exception {
        if ("CLOSE".equalsIgnoreCase(textMessage.getPayload())) {
            session.close();
        }
        try {
            Message message = gson.fromJson(textMessage.getPayload(), Message.class);
            switch (message.action) {
                case NEW_GAME -> this.newGame(message, session);
                case JOIN_GAME -> this.joinGame(message, session);
            }
        } catch (Exception e) {
            logger.error("EXCEPTION OCCURRED");
            logger.error( "failed! ", e );
        }
    }
    private void newGame(Message message, WebSocketSession session) throws IOException {
        gameController.addGame(message.gameId);
        addWebSocketSessionIdToGame(message.gameId, session.getId());
        Game game = gameController.getGameById(message.gameId);
        sendMessage(message.gameId, new TextMessage(gson.toJson(game)));
    }

    private void joinGame(Message message, WebSocketSession session) throws Exception {
        Player assignedPlayer = gameController.joinGame(message.gameId, message.playerId);
        addWebSocketSessionIdToGame(message.gameId, session.getId());
        ReturnMessage returnMessage = new ReturnMessage();
        returnMessage.type = "joinGame";
        returnMessage.assignedPlayer = assignedPlayer;
        returnMessage.requestUUID = message.requestUUID;
        sendMessage(message.gameId, new TextMessage(gson.toJson(returnMessage)));
    }

    private void addWebSocketSessionIdToGame(Long gameId, String sessionId) {
        webSocketSessionIdsByGame.computeIfAbsent(gameId, k -> new ArrayList<String>());
        if (!webSocketSessionIdsByGame.get(gameId).contains(sessionId)) {
            webSocketSessionIdsByGame.get(gameId).add(sessionId);
        }
    }

    public ArrayList<String> getWebSocketSessionIdsByGame(Long gameId) {
        Game game = gameController.getGameById(gameId);
        if (game == null) {
            return null;
        }
        return webSocketSessionIdsByGame.get(gameId);
    }




}