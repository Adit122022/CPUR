// 6. Write a program to rearrange characters in a string so that no two same characters are adjacent (use PriorityQueue).
import java.util.*;

public class Q6_RearrangeString {

    static class CharFreq {
        char ch;
        int count;
        
        CharFreq(char ch, int count) {
            this.ch = ch;
            this.count = count;
        }
    }

    public static String rearrange(String s) {

        Map map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        PriorityQueue maxHeap = new PriorityQueue<>((a, b) -> b.count - a.count);
        
    
        for (Character key : map.keySet()) {
            maxHeap.offer(new CharFreq(key, map.get(key)));
        }

        StringBuilder result = new StringBuilder();
        CharFreq prev = null; 

        while (!maxHeap.isEmpty()) {
            CharFreq current = maxHeap.poll(); 
            
            result.append(current.ch); 
            current.count--; 

           
            if (prev != null && prev.count > 0) {
                maxHeap.offer(prev);
            }

          
            prev = current;
        }

        if (result.length() != s.length()) {
            return "";
        }

        return result.toString();
    }

    public static void main(String[] args) {
        String str1 = "aab";
        System.out.println("Original: " + str1 + " -> Rearranged: " + rearrange(str1)); 
       

        String str2 = "aaab";
        System.out.println("Original: " + str2 + " -> Rearranged: " + rearrange(str2)); 
    
    }
}