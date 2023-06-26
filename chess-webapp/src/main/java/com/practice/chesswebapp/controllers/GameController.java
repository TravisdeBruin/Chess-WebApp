package com.practice.chesswebapp.controllers;

import com.practice.chesswebapp.dtos.GameDto;
import com.practice.chesswebapp.entities.Game;
import com.practice.chesswebapp.exceptions.GameException;
import com.practice.chesswebapp.responses.MessageResponse;
import com.practice.chesswebapp.services.interfaces.GameService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/game")
@CrossOrigin(origins = "http://localhost:4200/")
public class GameController {

//    public static final String GAME_PATH = "api/v1/game";

    private final GameService gameService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @PostMapping("/create")
    public ResponseEntity<GameDto> createNewGame(@RequestBody GameDto gameDto){
        return ResponseEntity.ok(gameService.createNewGame(gameDto));
    }

    @PostMapping("/connect")
    public ResponseEntity<GameDto> connectToGame(@RequestBody GameDto gameDto) throws GameException {
        return ResponseEntity.ok(gameService.connectToGame(gameDto));
    }

    @PostMapping("/sow/{gameId}")
    public ResponseEntity<GameDto> sow(@PathVariable Long gameId) throws  GameException {
        GameDto gamedto = gameService.sow(gameId);

        simpMessagingTemplate.convertAndSend("/topic/game-progress/" + gamedto.getId(), gamedto);
        return ResponseEntity.ok(gamedto);
    }
}
