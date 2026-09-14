import java.util.*;

/**
 * Q1: Remove duplicates from a list while maintaining original order.
 * Approach: Use LinkedHashSet — it preserves insertion order and rejects duplicates.
 */
public class Q1_RemoveDuplicates {

    public static List<Integer> removeDuplicates(List<Integer> list) {
        // LinkedHashSet maintains insertion order and eliminates duplicates
        Set<Integer> seen = new LinkedHashSet<>(list);
        return new ArrayList<>(seen);
    }

    public static void main(String[] args) {
        List<Integer> original = Arrays.asList(4, 2, 7, 2, 1, 4, 9, 7, 3, 1);

        System.out.println("Original List : " + original);
        System.out.println("Without Dupes : " + removeDuplicates(original));

        // Edge cases
        List<Integer> allDupes = Arrays.asList(5, 5, 5, 5);
        System.out.println("\nAll duplicates  : " + allDupes);
        System.out.println("Without Dupes   : " + removeDuplicates(allDupes));

        List<Integer> noDupes = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println("\nNo duplicates   : " + noDupes);
        System.out.println("Without Dupes   : " + removeDuplicates(noDupes));
    }
}
