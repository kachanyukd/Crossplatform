package textanalyzer.service;

import textanalyzer.model.TextStats;

import java.util.*;
import java.util.stream.Collectors;

public class AnalyzerService {

    public static TextStats analyze(String text) {
        String[] words = text.split("\\W+");
        String[] sentences = text.split("[.!?]+");

        int totalWords = words.length;
        Set<String> uniqueWordsSet = new HashSet<>(Arrays.asList(words));
        int uniqueWords = uniqueWordsSet.size();

        int totalSentences = sentences.length;
        int totalPunctuation = text.replaceAll("[^\\p{Punct}]", "").length();

        double avgWordLength = Arrays.stream(words)
                .mapToInt(String::length)
                .average()
                .orElse(0.0);

        double avgSentenceLength = Arrays.stream(sentences)
                .mapToInt(s -> s.split("\\s+").length)
                .average()
                .orElse(0.0);

        Map<String, Integer> freqMap = new HashMap<>();
        for (String w : words) {
            if (!w.isBlank()) {
                w = w.toLowerCase();
                freqMap.put(w, freqMap.getOrDefault(w, 0) + 1);
            }
        }

        List<String> topWords = freqMap.entrySet().stream()
                .sorted((a, b) -> b.getValue() - a.getValue())
                .limit(10)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        return new TextStats(totalWords, uniqueWords, totalSentences, totalPunctuation, avgWordLength, avgSentenceLength, topWords);
    }
}