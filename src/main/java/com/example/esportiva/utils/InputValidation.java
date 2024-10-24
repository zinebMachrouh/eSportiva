package com.example.esportiva.utils;


import java.sql.Date;

public class InputValidation {
    public static boolean handleString(String input) {
        return input != null && !input.isEmpty();
    }

    public static boolean handleNumber(int input) {
        return input > 0;
    }

    public static boolean handleNumber(double input) {
        return input > 0;
    }

    public static boolean handleAge(int input) {
        return input >= 10 && input < 100;
    }

    public static boolean handleDate(String input){
        try {
            Date.valueOf(input);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static boolean handleGameDifficulty(String input) {
        return input.equalsIgnoreCase("easy") || input.equalsIgnoreCase("medium") || input.equalsIgnoreCase("hard") || input.equalsIgnoreCase("extreme");
    }

    public static boolean handleTournamentStatus(String input) {
        return input.equalsIgnoreCase("scheduled") || input.equalsIgnoreCase("ongoing") || input.equalsIgnoreCase("finished") || input.equalsIgnoreCase("cancelled");
    }

    public static boolean handleTeamRanking(int input) {
        return input >= 0 && input <= 100;
    }
}
