import java.util.*;

/**
 * Q4: Find the k most frequently occurring elements in an array.
 *
 * Approach (Bucket Sort — O(n)):
 *  1. Build a frequency map with HashMap.
 *  2. Use bucket sort: index = frequency, value = list of numbers with that frequency.
 *  3. Traverse buckets from highest to lowest to collect top-k elements.
 *
 * Alternative: Min-Heap approach (O(n log k)) also shown as a second method.
 */
public class Q4_TopKFrequent {

    // --- Bucket Sort approach: O(n) ---
    public static List<Integer> topKFrequentBucket(int[] nums, int k) {
        // Step 1: frequency map
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // Step 2: buckets indexed by frequency (max freq = nums.length)
        @SuppressWarnings("unchecked")
        List<Integer>[] buckets = new List[nums.length + 1];
        for (int i = 0; i <= nums.length; i++) {
            buckets[i] = new ArrayList<>();
        }
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            buckets[entry.getValue()].add(entry.getKey());
        }

        // Step 3: collect top-k from highest-frequency buckets
        List<Integer> result = new ArrayList<>();
        for (int freq = nums.length; freq >= 1 && result.size() < k; freq--) {
            result.addAll(buckets[freq]);
        }
        return result.subList(0, k);
    }

    // --- Min-Heap approach: O(n log k) ---
    public static List<Integer> topKFrequentHeap(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // Min-heap: keep only k highest frequencies
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap =
            new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));

        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            minHeap.offer(entry);
            if (minHeap.size() > k) {
                minHeap.poll(); // remove least frequent
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            result.add(0, minHeap.poll().getKey()); // insert at front for desc order
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 1, 1, 2, 2, 3};
        int   k1    = 2;
        System.out.println("Array  : " + Arrays.toString(nums1) + "  k = " + k1);
        System.out.println("Top-k (Bucket) : " + topKFrequentBucket(nums1, k1));
        System.out.println("Top-k (Heap)   : " + topKFrequentHeap(nums1, k1));

        System.out.println();

        int[] nums2 = {4, 4, 4, 6, 6, 7, 7, 7, 7, 8};
        int   k2    = 3;
        System.out.println("Array  : " + Arrays.toString(nums2) + "  k = " + k2);
        System.out.println("Top-k (Bucket) : " + topKFrequentBucket(nums2, k2));
        System.out.println("Top-k (Heap)   : " + topKFrequentHeap(nums2, k2));
    }
}
