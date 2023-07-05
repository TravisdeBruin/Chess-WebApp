package com.practice.chesswebapp.services.implementations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practice.chesswebapp.dtos.ChessDto;
import com.practice.chesswebapp.dtos.GameDto;
import com.practice.chesswebapp.entities.Game;
import com.practice.chesswebapp.entities.User;
import com.practice.chesswebapp.enums.EGameState;
import com.practice.chesswebapp.exceptions.GameException;
import com.practice.chesswebapp.exceptions.NotFoundException;
import com.practice.chesswebapp.gameLogic.models.Chess;
import com.practice.chesswebapp.mappers.GameMapper;
import com.practice.chesswebapp.repositories.GameRepository;
import com.practice.chesswebapp.repositories.UserRepository;
import com.practice.chesswebapp.services.interfaces.ChessService;
import com.practice.chesswebapp.services.interfaces.GameService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final UserRepository userRepository;
    private final ChessService chessService;
    private final GameMapper gameMapper;

    @Override
    public GameDto createNewGame(GameDto gameDto) {
        Game newGame = gameMapper.gameDtoToGame(gameDto);
        newGame.setStatus(EGameState.NEW);

        if (gameDto.getPlayer() != null) {
            User user = userRepository.findByUsername(gameDto.getPlayer()).orElseThrow(() -> new RuntimeException("Error: User not found."));
            Random random = new Random();
            int randomInt = random.nextInt(2);
            if (randomInt == 0) {
                newGame.setWhitePlayer(user);
            } else {
                newGame.setBlackPlayer(user);
            }
            return gameMapper.gameToGameDto(gameRepository
                    .save(newGame));
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public GameDto connectToGame(GameDto gameDto) {
        Optional<Game> optionalGame = gameRepository.findById(gameDto.getId());
        optionalGame.orElseThrow(() ->new GameException("Game with provided id doesn't exist"));
        Game game = optionalGame.get();
        System.out.println(game);

        User user = userRepository.findByUsername(gameDto.getPlayer()).orElseThrow(() -> new NotFoundException("Error: User not found."));

        if (game.getWhitePlayer() != null && game.getBlackPlayer() != null) {
            throw new GameException("Game is not valid anymore");
        }

        if (game.getBlackPlayer() == null) {
            game.setBlackPlayer(user);
        } else {
            game.setWhitePlayer(user);
        }

        return gameMapper.gameToGameDto(gameRepository
                .save(game));
    }

    @Override
    public Chess startGame(Long gameId) {
        Optional<Game> optionalGame = gameRepository.findById(gameId);

        optionalGame.orElseThrow(() ->new GameException("Game with provided id doesn't exist"));
        Game game = optionalGame.get();

        return new Chess(game);
    }

    @Override
    public Chess chess(ChessDto chessDto) {
            Optional<Game> optionalGame = gameRepository.findById(chessDto.getGameId());

            optionalGame.orElseThrow(() ->new GameException("Game with provided id doesn't exist"));
            ObjectMapper objectMapper = new ObjectMapper();
//            Game game = optionalGame.get();

//            Chess updatedChessGame = chessService.chess(chessDto);
//            gameRepository.save(gameAfterSow);

            return chessService.chess(chessDto);
    }

}
