package com.example.esportiva.dto;

import com.example.esportiva.models.Game;
import com.example.esportiva.models.enums.GameDifficulty;

import java.util.UUID;

public class GameDTO {
    private UUID id;
    private String name;
    private GameDifficulty difficulty;
    private Integer matchDuration;

    public GameDTO() {
    }

    public GameDTO(UUID id, String name, GameDifficulty difficulty, Integer matchDuration) {
        this.id = id;
        this.name = name;
        this.difficulty = difficulty;
        this.matchDuration = matchDuration;
    }

    public GameDTO(String name, GameDifficulty difficulty, Integer matchDuration) {
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

    public Integer getMatchDuration() {
        return matchDuration;
    }
    public void setMatchDuration(Integer matchDuration) {
        this.matchDuration = matchDuration;
    }

    @Override
    public String toString() {
        return "GameDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", difficulty=" + difficulty +
                ", matchDuration=" + matchDuration +
                '}';
    }

    public Game dtoToModel() {
        return new Game(this.id, this.name, this.difficulty, this.matchDuration);
    }

    public static GameDTO modelToDTO(Game game) {
        return new GameDTO(game.getId(), game.getName(), game.getDifficulty(), game.getMatchDuration());
    }
}
