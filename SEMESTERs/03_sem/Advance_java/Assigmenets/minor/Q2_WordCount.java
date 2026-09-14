import java.util.*;

/**
 * Q2: Count how many times each word appears in a sentence.
 * Approach: Split the sentence into words, then use a HashMap to tally counts.
 */
public class Q2_WordCount {

    public static Map<String, Integer> countWords(String sentence) {
        Map<String, Integer> wordCount = new LinkedHashMap<>();

        // Normalize: lowercase, strip punctuation, split on whitespace
        String[] words = sentence.toLowerCase()
                                 .replaceAll("[^a-z0-9\\s]", "")
                                 .trim()
                                 .split("\\s+");

        for (String word : words) {
            if (!word.isEmpty()) {
                wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
            }
        }
        return wordCount;
    }

    public static void main(String[] args) {
        String sentence = "To be or not to be that is the question to be answered";

        System.out.println("Sentence: \"" + sentence + "\"\n");
        System.out.println(String.format("%-15s %s", "Word", "Count"));
        System.out.println("-".repeat(25));

        Map<String, Integer> counts = countWords(sentence);
        counts.forEach((word, count) ->
            System.out.printf("%-15s %d%n", word, count)
        );

        // Bonus: find the most frequent word
        String mostFrequent = Collections.max(counts.entrySet(),
                                Map.Entry.comparingByValue()).getKey();
        System.out.println("\nMost frequent word: \"" + mostFrequent
                + "\" (" + counts.get(mostFrequent) + " times)");
    }
}
