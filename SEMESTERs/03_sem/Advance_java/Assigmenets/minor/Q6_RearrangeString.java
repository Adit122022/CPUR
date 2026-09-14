import java.util.*;

/**
 * Q6: Rearrange characters so no two adjacent characters are the same.
 *     Must use PriorityQueue (Max-Heap).
 *
 * Algorithm (Greedy + Max-Heap):
 *  1. Build a frequency map for each character.
 *  2. Add all (char, freq) pairs to a Max-Heap ordered by frequency.
 *  3. At each step:
 *       a. Poll the most-frequent character (prev).
 *       b. Append it to the result.
 *       c. Poll the second most-frequent character (curr) and append it.
 *       d. Decrement counts; re-insert back if count > 0.
 *       e. If only one character type remains and its freq > 1, it's impossible.
 *
 * Time : O(n log k) — k unique characters, at most 26
 * Space: O(k)
 */
public class Q6_RearrangeString {

    public static String rearrange(String s) {
        if (s == null || s.isEmpty()) return s;

        // Step 1: frequency map
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        // Step 2: Max-Heap by frequency
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
            (a, b) -> b[1] - a[1]          // sort descending by count
        );
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            maxHeap.offer(new int[]{entry.getKey(), entry.getValue()});
        }

        StringBuilder result = new StringBuilder();

        // Step 3: Greedy placement
        while (maxHeap.size() >= 2) {
            int[] first  = maxHeap.poll();   // most frequent
            int[] second = maxHeap.poll();   // second most frequent

            result.append((char) first[0]);
            result.append((char) second[0]);

            if (--first[1]  > 0) maxHeap.offer(first);
            if (--second[1] > 0) maxHeap.offer(second);
        }

        // One character type left
        if (!maxHeap.isEmpty()) {
            int[] last = maxHeap.poll();
            if (last[1] > 1) {
                // Impossible: e.g., "aaa" cannot be rearranged
                return "IMPOSSIBLE — cannot rearrange \"" + s + "\"";
            }
            result.append((char) last[0]);
        }

        return result.toString();
    }

    public static void main(String[] args) {
        String[] tests = {"aab", "aaab", "aaabb", "abcdef", "aaaabc"};

        for (String test : tests) {
            String out = rearrange(test);
            System.out.println("Input  : " + test);
            System.out.println("Output : " + out);

            // Validate (no two adjacent same chars)
            boolean valid = !out.startsWith("IMPOSSIBLE");
            if (valid) {
                for (int i = 1; i < out.length(); i++) {
                    if (out.charAt(i) == out.charAt(i - 1)) {
                        valid = false;
                        break;
                    }
                }
            }
            System.out.println("Valid  : " + valid);
            System.out.println();
        }
    }
}
