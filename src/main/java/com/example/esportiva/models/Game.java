package com.example.esportiva.models;

import com.example.esportiva.models.enums.GameDifficulty;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "difficulty", nullable = false)
    @Enumerated(EnumType.STRING)
    private GameDifficulty difficulty;

    @Column(name = "matchDuration", nullable = false)
    private Integer matchDuration;

    public Game() {
    }

    public Game(String name, GameDifficulty difficulty, int matchDuration) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.difficulty = difficulty;
        this.matchDuration = matchDuration;
    }

    public Game(UUID id, String name, GameDifficulty difficulty, int matchDuration) {
        this.id = id;
        this.name = name;
        this.difficulty = difficulty;
        this.matchDuration = matchDuration;
    }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public GameDifficulty getDifficulty() {
        return difficulty;
    }
    public void setDifficulty(GameDifficulty difficulty) {
        this.difficulty = difficulty;
    }

    public int getMatchDuration() {
        return matchDuration;
    }
    public void setMatchDuration(int matchDuration) {
        this.matchDuration = matchDuration;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", difficulty=" + difficulty +
                ", matchDuration=" + matchDuration +
                '}';
    }

}
