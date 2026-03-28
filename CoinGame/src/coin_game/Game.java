package coin_game;

import java.util.Random;
import java.util.Scanner;

public class Game {
    private final Scanner scanner = new Scanner(System.in);
    private final Random random = new Random();

    private int coins;
    private boolean userTurn;

    public void start() {
        coins = random.nextInt(16) + 5; // від 5 до 20
        userTurn = random.nextBoolean();

        System.out.println("=== Гра в монети ===");
        System.out.println("Початкова кількість монет: " + coins);
        System.out.println(userTurn ? "Першим ходить користувач." : "Першим ходить комп'ютер.");

        while (coins > 0) {
            System.out.println("\nЗалишилось монет: " + coins);

            if (userTurn) {
                userMove();
                if (coins == 0) {
                    System.out.println("Користувач забрав останню монету. Ви перемогли!");
                    break;
                }
            } else {
                computerMove();
                if (coins == 0) {
                    System.out.println("Комп'ютер забрав останню монету. Комп'ютер переміг!");
                    break;
                }
            }

            userTurn = !userTurn;
        }

        scanner.close();
    }

    private void userMove() {
        int taken;

        do {
            System.out.print("Ваш хід. Візьміть 1 або 2 монети: ");
            taken = scanner.nextInt();
        } while (!isValidMove(taken));

        coins -= taken;
        System.out.println("Ви взяли: " + taken);
    }

    private void computerMove() {
        int taken = ComputerStrategy.chooseMove(coins);
        coins -= taken;
        System.out.println("Хід комп'ютера: " + taken);
    }

    private boolean isValidMove(int taken) {
        return taken >= 1 && taken <= 2 && taken <= coins;
    }
}