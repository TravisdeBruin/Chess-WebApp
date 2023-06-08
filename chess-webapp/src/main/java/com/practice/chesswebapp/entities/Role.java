package com.practice.chesswebapp.entities;

import com.practice.chesswebapp.enums.ERole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="roles")
public class Role
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    @Enumerated(EnumType.STRING)
    private ERole name;

    @ManyToMany(mappedBy="roles")
    private List<User> users;
}