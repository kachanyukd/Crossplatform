package com.yourname.rpw;

public enum Move {

    WELL,
    SCISSORS,
    PAPER;

    public boolean beats(Move other) {
        return (this == WELL && other == SCISSORS) ||
                (this == SCISSORS && other == PAPER) ||
                (this == PAPER && other == WELL);
    }
}