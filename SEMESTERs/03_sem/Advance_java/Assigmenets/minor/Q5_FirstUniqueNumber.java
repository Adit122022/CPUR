// 5. Write a program to find the first unique number in a stream of numbers (you can only traverse once).

import java.util.*;

public class Q5_FirstUniqueNumber {

    public static int firstUnique(int[] stream) {

        Map<Integer, Integer> map = new LinkedHashMap<>();

        for (int num : stream) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int num : stream) {
            if (map.get(num) == 1) {
                return num;
            }
        }

        return -1;
    }

    public static void main(String[] args) {

        int[] stream = {2, 3, 5, 2, 3, 7, 5, 8};

        int result = firstUnique(stream);

        System.out.println("Stream: " + Arrays.toString(stream));
        System.out.println("First Unique: " + result);
    }
}