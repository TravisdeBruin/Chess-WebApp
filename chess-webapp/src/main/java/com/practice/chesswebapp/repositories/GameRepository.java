package com.practice.chesswebapp.repositories;

import com.practice.chesswebapp.enums.EGameState;
import org.springframework.data.jpa.repository.JpaRepository;
import com.practice.chesswebapp.entities.Game;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    Optional<Game> findFirstByStatusAndBlackPlayerIsNull(String status);
    Optional<Game> findFirstByStatusAndWhitePlayerIsNull(String status);
}
