package com.practice.chesswebapp.services.implementations;

import com.practice.chesswebapp.dtos.GameDto;
import com.practice.chesswebapp.entities.Game;
import com.practice.chesswebapp.entities.User;
import com.practice.chesswebapp.enums.EGameState;
import com.practice.chesswebapp.exceptions.GameException;
import com.practice.chesswebapp.exceptions.NotFoundException;
import com.practice.chesswebapp.mappers.GameMapper;
import com.practice.chesswebapp.repositories.GameRepository;
import com.practice.chesswebapp.repositories.UserRepository;
import com.practice.chesswebapp.services.interfaces.GameService;
import com.practice.chesswebapp.services.interfaces.SowService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final UserRepository userRepository;
    private final GameMapper gameMapper;
    private final SowService sowService;

    public GameServiceImpl(GameRepository gameRepository, GameMapper gameMapper, UserRepository userRepository, SowService sowService) {
        this.gameRepository = gameRepository;
        this.gameMapper = gameMapper;
        this.userRepository = userRepository;
        this.sowService = sowService;
    }

    @Override
    public GameDto createNewGame(GameDto gameDto) {

        Game newGame = gameMapper.gameDtoToGame(gameDto);

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

//        List<String> usernames = gameDto.getUsernames();
//        List<User> users = new ArrayList<>();
//
//        if (usernames == null) {
//            throw new NotFoundException();
//        } else {
//            usernames.forEach(username -> {
//                User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Error: User not found."));
//                users.add(user);
//            });
//        }


//        while (!users.isEmpty()) {
//            User randomUser =  users.get(random.nextInt(users.size()));
//            if (newGame.getWhitePlayer() == null) {
//                newGame.setWhitePlayer(randomUser);
//            } else {
//                newGame.setBlackPlayer(randomUser);
//            }
//            users.remove(randomUser);
//        }
    }

    public GameDto connectToGame(GameDto gameDto) {
        Optional<Game> optionalGame = gameRepository.findById(gameDto.getId());
        optionalGame.orElseThrow(() ->new GameException("Game with provided id doesn't exist"));
        Game game = optionalGame.get();

        User user = userRepository.findByUsername(gameDto.getPlayer()).orElseThrow(() -> new RuntimeException("Error: User not found."));

        if (game.getWhitePlayer() != null || game.getBlackPlayer() != null) {
            throw new GameException("Game is not valid anymore");
        }

        if (game.getBlackPlayer() == null) {
            game.setBlackPlayer(user);
        } else {
            game.setWhitePlayer(user);
        }

        game.setStatus(EGameState.STARTED.toString());
        return gameMapper.gameToGameDto(gameRepository
                .save(game));
    }

    public GameDto sow(Long gameId) {
        Optional<Game> optionalGame = gameRepository.findById(gameId);

        optionalGame.orElseThrow(() ->new GameException("Game with provided id doesn't exist"));
        Game game = optionalGame.get();

        Game gameAfterSow = sowService.sow(game);

        gameRepository.save(gameAfterSow);

        return gameMapper.gameToGameDto(gameAfterSow);
    }
}
