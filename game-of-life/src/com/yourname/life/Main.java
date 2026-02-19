package com.yourname.life;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        int rows = 20;
        int cols = 40;

        Board board = new Board(rows, cols);
        board.randomInitialize();

        ConsoleRenderer renderer = new ConsoleRenderer();

        while (true) {
            renderer.render(board);
            board.nextGeneration();
            Thread.sleep(300);
        }
    }
}