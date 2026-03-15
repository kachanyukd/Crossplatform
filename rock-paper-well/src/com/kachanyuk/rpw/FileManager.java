package com.kachanyuk.rpw;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private static final String FILE_NAME = "game_results.txt";

    public void saveSessionResult(int sessionNumber, String sessionResult, List<Move> userMoves, List<Move> computerMoves) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write("Session=" + sessionNumber);
            writer.write(";Result=" + sessionResult);
            writer.write(";UserMoves=" + movesToString(userMoves));
            writer.write(";ComputerMoves=" + movesToString(computerMoves));
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Помилка запису у файл: " + e.getMessage());
        }
    }

    public List<Move> loadPreviousUserMoves() {
        List<Move> moves = new ArrayList<>();
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return moves;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                for (String part : parts) {
                    if (part.startsWith("UserMoves=")) {
                        String movePart = part.substring("UserMoves=".length());
                        String[] moveNames = movePart.split(",");

                        for (String moveName : moveNames) {
                            moveName = moveName.trim();
                            if (!moveName.isEmpty()) {
                                Move move = parseMove(moveName);
                                if (move != null) {
                                    moves.add(move);
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Помилка читання файлу: " + e.getMessage());
        }

        return moves;
    }

    public void showStatistics() {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("Файл зі статистикою ще не створений.");
            return;
        }

        int userWins = 0;
        int computerWins = 0;
        int totalSessions = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                totalSessions++;

                if (line.contains("Result=USER_WIN")) {
                    userWins++;
                } else if (line.contains("Result=COMPUTER_WIN")) {
                    computerWins++;
                }
            }

            System.out.println("\n=== Статистика ===");
            System.out.println("Зіграно партій: " + totalSessions);
            System.out.println("Перемог користувача: " + userWins);
            System.out.println("Перемог комп'ютера: " + computerWins);

            if (totalSessions > 0) {
                double userPercent = userWins * 100.0 / totalSessions;
                double computerPercent = computerWins * 100.0 / totalSessions;

                System.out.printf("Відсоток перемог користувача: %.2f%%%n", userPercent);
                System.out.printf("Відсоток перемог комп'ютера: %.2f%%%n", computerPercent);
            }
        } catch (IOException e) {
            System.out.println("Помилка читання статистики: " + e.getMessage());
        }
    }

    private String movesToString(List<Move> moves) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < moves.size(); i++) {
            sb.append(moves.get(i).name());
            if (i < moves.size() - 1) {
                sb.append(",");
            }
        }

        return sb.toString();
    }

    private Move parseMove(String moveName) {
        try {
            return Move.valueOf(moveName);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}