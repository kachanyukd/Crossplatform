package textanalyzer.model;

import java.util.List;

public class TextStats {
    private int totalWords;
    private int uniqueWords;
    private int totalSentences;
    private int totalPunctuation;
    private double avgWordLength;
    private double avgSentenceLength;
    private List<String> topWords;

    public TextStats(int totalWords, int uniqueWords, int totalSentences, int totalPunctuation, double avgWordLength, double avgSentenceLength, List<String> topWords) {
        this.totalWords = totalWords;
        this.uniqueWords = uniqueWords;
        this.totalSentences = totalSentences;
        this.totalPunctuation = totalPunctuation;
        this.avgWordLength = avgWordLength;
        this.avgSentenceLength = avgSentenceLength;
        this.topWords = topWords;
    }

    @Override
    public String toString() {
        return "Text Statistics:\n" +
                "Total words: " + totalWords + "\n" +
                "Unique words: " + uniqueWords + "\n" +
                "Total sentences: " + totalSentences + "\n" +
                "Total punctuation: " + totalPunctuation + "\n" +
                "Average word length: " + String.format("%.2f", avgWordLength) + "\n" +
                "Average sentence length: " + String.format("%.2f", avgSentenceLength) + "\n" +
                "Top 10 words: " + topWords;
    }
}