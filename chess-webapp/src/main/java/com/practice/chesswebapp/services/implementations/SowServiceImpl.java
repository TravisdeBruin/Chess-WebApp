package com.practice.chesswebapp.services.implementations;

import com.practice.chesswebapp.entities.Game;
import com.practice.chesswebapp.services.interfaces.SowService;
import org.springframework.stereotype.Service;

@Service
public class SowServiceImpl implements SowService {
    public Game sow(Game game) {
        return game;
    }
}
