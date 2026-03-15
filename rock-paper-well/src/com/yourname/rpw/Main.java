package com.yourname.rpw;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ComputerPlayer computerPlayer = new ComputerPlayer();
    private static final FileManager fileManager = new FileManager();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== МЕНЮ ===");
            System.out.println("1. Почати гру");
            System.out.println("2. Переглянути статистику");
            System.out.println("3. Вийти");
            System.out.print("Оберіть пункт: ");

            int menuChoice = readInt();

            switch (menuChoice) {
                case 1:
                    startGame();
                    break;
                case 2:
                    fileManager.showStatistics();
                    break;
                case 3:
                    System.out.println("Програму завершено.");
                    return;
                default:
                    System.out.println("Невірний вибір. Спробуйте ще раз.");
            }
        }
    }

    private static void startGame() {
        System.out.print("Введіть кількість seансів: ");
        int sessionsCount = readPositiveInt();

        System.out.println("\nОберіть режим гри комп'ютера:");
        System.out.println("1. Випадкові ходи");
        System.out.println("2. З урахуванням ходів користувача протягом поточного сеансу");
        System.out.println("3. З урахуванням ходів користувача за попередні сеанси (з файлу)");
        System.out.print("Ваш вибір: ");

        int mode = readMode();

        List<Move> previousSessionsUserMoves = new ArrayList<>();
        if (mode == 3) {
            previousSessionsUserMoves = fileManager.loadPreviousUserMoves();
            if (previousSessionsUserMoves.isEmpty()) {
                System.out.println("Даних з попередніх сеансів немає. Комп'ютер тимчасово використовує випадкові ходи.");
            }
        }

        for (int session = 1; session <= sessionsCount; session++) {
            System.out.println("\n=== Сеанс " + session + " ===");

            List<Move> currentSessionUserMoves = new ArrayList<>();
            List<Move> currentSessionComputerMoves = new ArrayList<>();

            while (true) {
                Move userMove = readUserMove();
                Move computerMove = computerPlayer.getMove(mode, currentSessionUserMoves, previousSessionsUserMoves);

                currentSessionUserMoves.add(userMove);
                currentSessionComputerMoves.add(computerMove);

                System.out.println("Ваш хід: " + userMove.getDisplayName());
                System.out.println("Хід комп'ютера: " + computerMove.getDisplayName());

                GameResult result = determineWinner(userMove, computerMove);

                if (result == GameResult.DRAW) {
                    System.out.println("Нічия. Повторюємо хід у межах цього сеансу.");
                } else if (result == GameResult.USER_WIN) {
                    System.out.println("Ви перемогли у цьому сеансі!");
                    fileManager.saveSessionResult(session, result.name(), currentSessionUserMoves, currentSessionComputerMoves);
                    break;
                } else {
                    System.out.println("Комп'ютер переміг у цьому сеансі.");
                    fileManager.saveSessionResult(session, result.name(), currentSessionUserMoves, currentSessionComputerMoves);
                    break;
                }
            }
        }

        System.out.println("\nГру завершено.");
    }

    private static Move readUserMove() {
        while (true) {
            System.out.println("\nОберіть знак:");
            System.out.println("1. Криниця");
            System.out.println("2. Ножиці");
            System.out.println("3. Папір");
            System.out.print("Ваш вибір: ");

            int choice = readInt();
            Move move = Move.fromInt(choice);

            if (move != null) {
                return move;
            }

            System.out.println("Невірний вибір. Введіть 1, 2 або 3.");
        }
    }

    private static int readMode() {
        while (true) {
            int mode = readInt();
            if (mode >= 1 && mode <= 3) {
                return mode;
            }
            System.out.print("Невірний режим. Введіть 1, 2 або 3: ");
        }
    }

    private static int readPositiveInt() {
        while (true) {
            int value = readInt();
            if (value > 0) {
                return value;
            }
            System.out.print("Введіть число більше 0: ");
        }
    }

    private static int readInt() {
        while (true) {
            try {
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Потрібно ввести ціле число. Спробуйте ще раз: ");
            }
        }
    }

    private static GameResult determineWinner(Move userMove, Move computerMove) {
        if (userMove == computerMove) {
            return GameResult.DRAW;
        }

        if ((userMove == Move.WELL && computerMove == Move.SCISSORS) ||
                (userMove == Move.SCISSORS && computerMove == Move.PAPER) ||
                (userMove == Move.PAPER && computerMove == Move.WELL)) {
            return GameResult.USER_WIN;
        }

        return GameResult.COMPUTER_WIN;
    }
}