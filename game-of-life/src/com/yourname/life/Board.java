package com.yourname.life;

import java.util.Random;

public class Board {

    private final int rows;
    private final int cols;
    private boolean[][] grid;

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new boolean[rows][cols];
    }

    public void randomInitialize() {
        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = random.nextBoolean();
            }
        }
    }

    public void nextGeneration() {
        boolean[][] newGrid = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                int aliveNeighbors = countAliveNeighbors(i, j);
                boolean isAlive = grid[i][j];

                boolean survives = aliveNeighbors == 3 ||
                        (isAlive && aliveNeighbors == 2);

                newGrid[i][j] = survives;
            }
        }

        grid = newGrid;
    }

    private int countAliveNeighbors(int row, int col) {
        int count = 0;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {

                if (i == 0 && j == 0) continue;

                int r = row + i;
                int c = col + j;

                if (r >= 0 && r < rows && c >= 0 && c < cols && grid[r][c]) {
                    count++;
                }
            }
        }

        return count;
    }

    public boolean[][] getGrid() {
        return grid;
    }
}