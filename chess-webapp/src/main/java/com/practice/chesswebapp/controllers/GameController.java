package com.practice.chesswebapp.controllers;

import com.practice.chesswebapp.dtos.ChessDto;
import com.practice.chesswebapp.dtos.GameDto;
import com.practice.chesswebapp.exceptions.GameException;
import com.practice.chesswebapp.gameLogic.models.Chess;
import com.practice.chesswebapp.services.implementations.GameServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/game")
public class GameController {
    private final GameServiceImpl gameService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @PostMapping("/create")
    public ResponseEntity<GameDto> create(@RequestBody GameDto gameDto) {
        log.info("create game request: {}", gameDto);
        return ResponseEntity.ok(gameService.createNewGame(gameDto));
    }

    @PutMapping("/connect")
    public ResponseEntity<GameDto> connect(@RequestBody GameDto gameDto) {
        log.info("connect game request: {}", gameDto);
        return ResponseEntity.ok(gameService.connectToGame(gameDto));
    }

    @GetMapping("/start/{gameId}")
    public ResponseEntity<Chess> startGame(@PathVariable Long gameId) throws GameException {
        log.info("start game: {}", gameId);
        Chess chessGame = gameService.startGame(gameId);

//        simpMessagingTemplate.convertAndSend("/topic/game-progress/" + chessGame.getGameId(), chessGame);
        return ResponseEntity.ok(chessGame);
    }

    @ResponseBody
    @PostMapping("/chess")
    public ResponseEntity<Chess> chess(@RequestBody ChessDto chessDto) throws GameException {
        log.info("chess Move: {}", chessDto);
        Chess chessGame = gameService.chess(chessDto);

        simpMessagingTemplate.convertAndSend("/topic/game-progress/" + chessGame.getGameId(), chessGame);
        return ResponseEntity.ok(chessGame);
    }
}