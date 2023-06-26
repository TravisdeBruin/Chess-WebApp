package com.practice.chesswebapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameDto {
    private Long id;

    private String gameType;

    private String status;

    private List<String> moves = new ArrayList<>();

    private ArrayList<String[][]> gameStates;

    private ArrayList<String> usernames;

    private String player;
}