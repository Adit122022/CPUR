// Q3: Find two numbers that add up to a target using HashMap — O(n) time.
import java.util.*;


public class Q3_TwoSum {

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
    
        for(int i =0;i<nums.length;i++){
           int sum = target - nums[i];
            if(map.containsKey(sum)){
                return new int[] {map.get(sum),i};
            }
            map.put(nums[i],i);
        }
        return new int[] {};
    }

    public static void main(String[] args) {
        int[] arr = {2, 7, 11, 15};
        int t =9;
     System.out.println(Arrays.toString(twoSum(arr, t)));
        
    }
}
