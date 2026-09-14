// Q2: Count how many times each word appears in a sentence.
import java.util.*;


 
public class Q2_WordCount {
    public static void main(String[] args) {
        String sentence = "To be or not to be that is the question to be answered";

        String[] words = sentence.toLowerCase().split(" ");

        Map<String,Integer> counts = new HashMap<>();

        for (String word : words) {
            if (counts.containsKey(word)) {
                int oldCount = counts.get(word); 
                counts.put(word, oldCount + 1);
            } else {
                counts.put(word, 1);
            }
        }

        System.out.println(counts);
    }
}