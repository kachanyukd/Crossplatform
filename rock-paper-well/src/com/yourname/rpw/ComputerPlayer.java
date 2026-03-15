package com.yourname.rpw;
import java.util.List;
import java.util.Random;

public class ComputerPlayer {
    private final Random random = new Random();

    public Move getMove(int mode, List<Move> currentSessionUserMoves, List<Move> previousSessionsUserMoves) {
        switch (mode) {
            case 1:
                return getRandomMove();
            case 2:
                return getMoveBasedOnCurrentSession(currentSessionUserMoves);
            case 3:
                return getMoveBasedOnPreviousSessions(previousSessionsUserMoves);
            default:
                return getRandomMove();
        }
    }

    private Move getRandomMove() {
        int value = random.nextInt(3);
        if (value == 0) {
            return Move.WELL;
        } else if (value == 1) {
            return Move.SCISSORS;
        } else {
            return Move.PAPER;
        }
    }

    private Move getMoveBasedOnCurrentSession(List<Move> currentSessionUserMoves) {
        if (currentSessionUserMoves == null || currentSessionUserMoves.isEmpty()) {
            return getRandomMove();
        }

        Move predictedUserMove = getMostFrequentMove(currentSessionUserMoves);
        return Move.getWinningMoveAgainst(predictedUserMove);
    }

    private Move getMoveBasedOnPreviousSessions(List<Move> previousSessionsUserMoves) {
        if (previousSessionsUserMoves == null || previousSessionsUserMoves.isEmpty()) {
            return getRandomMove();
        }

        Move predictedUserMove = getMostFrequentMove(previousSessionsUserMoves);
        return Move.getWinningMoveAgainst(predictedUserMove);
    }

    private Move getMostFrequentMove(List<Move> moves) {
        int wellCount = 0;
        int scissorsCount = 0;
        int paperCount = 0;

        for (Move move : moves) {
            if (move == Move.WELL) {
                wellCount++;
            } else if (move == Move.SCISSORS) {
                scissorsCount++;
            } else if (move == Move.PAPER) {
                paperCount++;
            }
        }

        if (wellCount >= scissorsCount && wellCount >= paperCount) {
            return Move.WELL;
        } else if (scissorsCount >= wellCount && scissorsCount >= paperCount) {
            return Move.SCISSORS;
        } else {
            return Move.PAPER;
        }
    }
}