// Q1: Remove duplicates from a list while maintaining original order.
import java.util.*;

 
public class Q1_RemoveDuplicates {

    public static List<Integer> rmDub(List<Integer> li) {
        Set<Integer> a = new LinkedHashSet<>(li);
        return new ArrayList<>(a);
    }

    public static void main(String[] args) {
        List<Integer> li = Arrays.asList(4, 2, 7, 2, 1, 4, 9, 7, 3, 1);
        System.out.println("Original List : " + li);
        System.out.println("Without Dupes : " + rmDub(li));

    
    }
}
