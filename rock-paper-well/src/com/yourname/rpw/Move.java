package com.yourname.rpw;

public enum Move {
    WELL("Криниця"),
    SCISSORS("Ножиці"),
    PAPER("Папір");

    private final String displayName;

    Move(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static Move fromInt(int choice) {
        switch (choice) {
            case 1:
                return WELL;
            case 2:
                return SCISSORS;
            case 3:
                return PAPER;
            default:
                return null;
        }
    }

    public static Move getWinningMoveAgainst(Move move) {
        switch (move) {
            case WELL:
                return PAPER;
            case SCISSORS:
                return WELL;
            case PAPER:
                return SCISSORS;
            default:
                return null;
        }
    }
}