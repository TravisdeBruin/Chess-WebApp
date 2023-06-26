package com.practice.chesswebapp.services.interfaces;

import com.practice.chesswebapp.entities.Game;
import org.springframework.stereotype.Service;

@Service
public interface SowService {
    Game sow(Game game);
}
