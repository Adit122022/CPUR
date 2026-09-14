import java.util.*;

/**
 * Q3: Find two numbers that add up to a target using HashMap — O(n) time.
 *
 * Key Idea:
 *   For each element x, we need (target - x).
 *   Store every visited element in a HashMap.
 *   If (target - x) is already in the map, we found our pair.
 *
 * Time  : O(n)  — single pass through the array
 * Space : O(n)  — HashMap stores at most n elements
 */
public class Q3_TwoSum {

    /**
     * Returns the indices [i, j] such that nums[i] + nums[j] == target.
     * Returns [-1, -1] if no such pair exists.
     */
    public static int[] twoSum(int[] nums, int target) {
        // key = number seen, value = its index
        Map<Integer, Integer> seen = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            if (seen.containsKey(complement)) {
                return new int[]{seen.get(complement), i};
            }
            seen.put(nums[i], i);
        }
        return new int[]{-1, -1}; // no pair found
    }

    public static void main(String[] args) {
        int[][] testArrays = {
            {2, 7, 11, 15},
            {3, 2, 4},
            {1, 5, 3, 7, 9},
            {0, -1, 2, -3, 1}
        };
        int[] targets = {9, 6, 10, -2};

        for (int t = 0; t < testArrays.length; t++) {
            int[] nums   = testArrays[t];
            int   target = targets[t];
            int[] result = twoSum(nums, target);

            System.out.println("Array  : " + Arrays.toString(nums));
            System.out.println("Target : " + target);
            if (result[0] == -1) {
                System.out.println("Result : No pair found");
            } else {
                System.out.printf("Result : nums[%d] + nums[%d] = %d + %d = %d%n",
                        result[0], result[1],
                        nums[result[0]], nums[result[1]],
                        target);
            }
            System.out.println();
        }
    }
}
