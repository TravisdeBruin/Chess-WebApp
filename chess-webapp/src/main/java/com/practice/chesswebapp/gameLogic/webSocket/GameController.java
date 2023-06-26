package com.practice.chesswebapp.gameLogic.webSocket;

import com.practice.chesswebapp.gameLogic.Storage;
import com.practice.chesswebapp.gameLogic.models.Game;
import com.practice.chesswebapp.gameLogic.models.Player;

import static com.practice.chesswebapp.enums.EColour.*;

public class GameController {
    public void addGame(Long gameId) {
        Game game = new Game(gameId);
        Storage.put(game, gameId.toString());
    }

    public Game getGameById(Long gameId) {
        return (Game) Storage.get(gameId.toString());
    }

    public Player joinGame(Long gameId, Long playerId) throws Exception {
        Game game = this.getGameById(gameId);
        if (game == null) {
            return null;
        }
        Player player = game.getPlayerById(playerId);
        if (player != null) {
            if (game.getWhitePlayer().getUser().getId().equals(playerId)) {
                player.setColour(WHITE);
            }else if (game.getBlackPlayer().getUser().getId().equals(playerId)) {
                player.setColour(BLACK);
            }
        } else {
           throw new InstantiationException();
        }
        Storage.put(game, gameId.toString());
        return player;
    }
}
