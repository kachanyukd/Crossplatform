package textanalyzer.ui;

import textanalyzer.model.TextStats;
import textanalyzer.service.AnalyzerService;
import textanalyzer.util.FileUtil;

import java.io.IOException;
import java.util.Scanner;

public class ConsoleUI {

    public static void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter path to the text file:");
        String path = scanner.nextLine();

        try {
            String content = FileUtil.readFile(path);
            TextStats stats = AnalyzerService.analyze(content);
            System.out.println(stats);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}