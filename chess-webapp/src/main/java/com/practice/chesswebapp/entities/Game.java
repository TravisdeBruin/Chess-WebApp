package com.practice.chesswebapp.entities;

import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="games")
public class Game {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String gameType;

    @Column(nullable=false)
    private String result;

    @Column(nullable=false)
    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    private List<String> moves = new ArrayList<>();


//Change to blob storage
    @Column(nullable=false)
    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    private ArrayList<String[][]> gameStates;

    @CreationTimestamp
    private LocalDateTime dateStarted;

    @Timestamp
    private LocalDateTime updatedDate;

    @ManyToOne
    private User whitePlayer;

    @ManyToOne
    private User blackPlayer;
}
