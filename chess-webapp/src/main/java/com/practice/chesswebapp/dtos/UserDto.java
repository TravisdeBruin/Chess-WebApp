package com.practice.chesswebapp.dtos;

import com.practice.chesswebapp.entities.Game;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String surname;

    @NotEmpty
    @Column(unique = true, nullable = false)
    private String username;

    @NotEmpty(message = "Email should not be empty")
    @Email
    @Column(unique = true, nullable = false)
    private String email;

    @NotEmpty(message = "Password should not be empty")
    @Column(nullable = false)
    private String password;

    private Integer elo;

}
