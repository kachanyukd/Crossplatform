package com.yourname.rpw;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Game game = new Game();

        System.out.println("1 - Play");
        System.out.println("2 - Show statistics");

        int option = scanner.nextInt();

        if (option == 2) {
            Statistics.showStatistics();
            return;
        }

        System.out.println("Number of sessions:");
        int sessions = scanner.nextInt();

        System.out.println("Computer mode:");
        System.out.println("1 - Random");
        System.out.println("2 - Adaptive");

        int mode = scanner.nextInt();

        game.start(sessions, mode);
    }
}