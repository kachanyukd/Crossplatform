package textanalyzer;

import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter path to the text file:");
        String path = scanner.nextLine();

        try {
            String text = Files.readString(Path.of(path), StandardCharsets.UTF_8);
            analyze(text);
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public static void analyze(String text) {
        String[] words = text.split("\\W+");
        String[] sentences = text.split("[.!?]+");

        int totalWords = words.length;
        int uniqueWords = (int) Arrays.stream(words).distinct().count();
        int totalSentences = sentences.length;
        int totalPunctuation = text.replaceAll("[^\\p{Punct}]", "").length();

        double avgWordLength = Arrays.stream(words)
                .mapToInt(String::length)
                .average()
                .orElse(0);

        double avgSentenceLength = Arrays.stream(sentences)
                .mapToInt(s -> s.split("\\s+").length)
                .average()
                .orElse(0);

        Map<String, Long> freq = Arrays.stream(words)
                .filter(w -> !w.isBlank())
                .map(String::toLowerCase)
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()));

        List<String> topWords = freq.entrySet().stream()
                .sorted((a, b) -> Long.compare(b.getValue(), a.getValue()))
                .limit(10)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        System.out.println("Text Statistics:");
        System.out.println("Total words: " + totalWords);
        System.out.println("Unique words: " + uniqueWords);
        System.out.println("Total sentences: " + totalSentences);
        System.out.println("Total punctuation: " + totalPunctuation);
        System.out.printf("Average word length: %.2f%n", avgWordLength);
        System.out.printf("Average sentence length: %.2f%n", avgSentenceLength);
        System.out.println("Top 10 words: " + topWords);
    }
}