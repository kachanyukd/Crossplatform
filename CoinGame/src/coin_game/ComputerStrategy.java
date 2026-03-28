package coin_game;

public class ComputerStrategy {
    public static int chooseMove(int coins) {
        if (coins == 1) {
            return 1;
        }

        if (coins % 3 == 0) {
            return 1;
        }

        return coins % 3;
    }
}