package com.practice.chesswebapp.responses;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class JwtResponse {
    private final String token;
    private String type = "Bearer";
    private final Long id;
    private final String username;
    private final String email;
    private final List<String> roles;
}
