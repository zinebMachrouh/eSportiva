package com.example.esportiva.models.enums;

public enum GameDifficulty {
    EASY(1),
    MEDIUM(2),
    HARD(3),
    EXTREME(4);

    private final int multiplier;

    GameDifficulty(int multiplier) {
        this.multiplier = multiplier;
    }

    public int getMultiplier() {
        return multiplier;
    }
}
