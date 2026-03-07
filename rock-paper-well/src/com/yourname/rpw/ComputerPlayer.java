package com.yourname.rpw;

import java.util.*;

public class ComputerPlayer {

    private Random random = new Random();
    private Map<Move, Integer> userMoves = new HashMap<>();

    public Move randomMove() {
        Move[] moves = Move.values();
        return moves[random.nextInt(moves.length)];
    }

    public Move adaptiveMove() {

        if (userMoves.isEmpty()) {
            return randomMove();
        }

        Move mostUsed = Collections.max(userMoves.entrySet(),
                Map.Entry.comparingByValue()).getKey();

        switch (mostUsed) {
            case WELL:
                return Move.PAPER;
            case SCISSORS:
                return Move.WELL;
            case PAPER:
                return Move.SCISSORS;
        }

        return randomMove();
    }

    public void recordUserMove(Move move) {
        userMoves.put(move, userMoves.getOrDefault(move, 0) + 1);
    }
}