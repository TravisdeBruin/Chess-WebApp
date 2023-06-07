package com.practice.chesswebapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.practice.chesswebapp.entities.Game;

public interface GameRepository extends JpaRepository<Game, Long> {
}
