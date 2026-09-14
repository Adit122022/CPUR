import java.util.*;

/**
 * Q5: Find the first unique number in a stream of numbers (single traversal only).
 *
 * Approach:
 *  - Use a LinkedHashMap to store <number → frequency>.
 *    LinkedHashMap preserves insertion order, which is crucial.
 *  - After processing the entire stream in one pass, iterate the map entries
 *    in insertion order and return the first entry whose frequency is 1.
 *
 * Why single traversal?
 *  We only read the stream once (the for-each loop). The final map walk is
 *  O(distinct elements) — NOT re-reading the stream.
 *
 * Time : O(n)  — one pass through the stream
 * Space: O(d)  — d = number of distinct elements
 */
public class Q5_FirstUniqueNumber {

    public static int firstUnique(int[] stream) {
        // LinkedHashMap preserves insertion (arrival) order
        Map<Integer, Integer> freqMap = new LinkedHashMap<>();

        // Single traversal of the stream
        for (int num : stream) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // First entry with frequency == 1 is the first unique number
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return -1; // no unique number found
    }

    public static void main(String[] args) {
        int[][] streams = {
            {2, 3, 5, 2, 3, 7, 5, 8},          // first unique → 7
            {9, 4, 9, 6, 7, 4},                  // first unique → 6
            {1, 1, 2, 2, 3, 3},                  // all duplicates → -1
            {10}                                  // single element → 10
        };

        for (int[] stream : streams) {
            int result = firstUnique(stream);
            System.out.println("Stream         : " + Arrays.toString(stream));
            System.out.println("First Unique   : " + (result == -1 ? "None" : result));
            System.out.println();
        }
    }
}
