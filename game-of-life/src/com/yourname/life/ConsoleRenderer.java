package com.yourname.life;

public class ConsoleRenderer {

    public void render(Board board) {

        System.out.print("\033[H\033[2J");
        System.out.flush();

        boolean[][] grid = board.getGrid();

        for (boolean[] row : grid) {
            for (boolean cell : row) {
                System.out.print(cell ? "█ " : ". ");
            }
            System.out.println();
        }
    }
}