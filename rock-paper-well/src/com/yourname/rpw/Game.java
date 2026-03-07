package com.yourname.rpw;

import java.util.Scanner;

public class Game {

    private Scanner scanner = new Scanner(System.in);
    private ComputerPlayer computer = new ComputerPlayer();

    public void start(int sessions, int mode) {

        for (int i = 0; i < sessions; i++) {

            System.out.println("\nRound " + (i + 1));

            Move userMove = getUserMove();
            Move computerMove = getComputerMove(mode);

            computer.recordUserMove(userMove);

            System.out.println("Computer: " + computerMove);

            if (userMove == computerMove) {
                System.out.println("Draw");
                Statistics.saveResult("DRAW");
            }
            else if (userMove.beats(computerMove)) {
                System.out.println("You win");
                Statistics.saveResult("WIN");
            }
            else {
                System.out.println("You lose");
                Statistics.saveResult("LOSS");
            }
        }
    }

    private Move getUserMove() {

        System.out.println("1 - WELL");
        System.out.println("2 - SCISSORS");
        System.out.println("3 - PAPER");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1: return Move.WELL;
            case 2: return Move.SCISSORS;
            case 3: return Move.PAPER;
        }

        return Move.WELL;
    }

    private Move getComputerMove(int mode) {

        if (mode == 1) {
            return computer.randomMove();
        }

        if (mode == 2) {
            return computer.adaptiveMove();
        }

        return computer.randomMove();
    }
}