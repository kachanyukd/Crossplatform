package com.yourname.rpw;

import java.io.*;
import java.util.*;

public class Statistics {

    private static final String FILE = "stats.txt";

    public static void saveResult(String result) {

        try (FileWriter fw = new FileWriter(FILE, true)) {
            fw.write(result + "\n");
        } catch (IOException e) {
            System.out.println("Error writing statistics");
        }
    }

    public static void showStatistics() {

        int wins = 0;
        int losses = 0;
        int draws = 0;

        try (Scanner scanner = new Scanner(new File(FILE))) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (line.equals("WIN")) wins++;
                else if (line.equals("LOSS")) losses++;
                else if (line.equals("DRAW")) draws++;
            }

        } catch (Exception e) {
            System.out.println("No statistics yet");
            return;
        }

        System.out.println("Wins: " + wins);
        System.out.println("Losses: " + losses);
        System.out.println("Draws: " + draws);
    }
}