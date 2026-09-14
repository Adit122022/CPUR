// Q7: 7. Write a program to flatten a nested list of integers (example: [[1,2],[3,[4,5]]] → [1,2,3,4,5])
import java.util.*;

public class Q7_FlattenNestedList {

    public static List<Integer> flatten(List<Object> nested) {

        List<Integer> result = new ArrayList<>();

        for (Object element : nested) {

            if (element instanceof Integer) {
                result.add((Integer) element);
            } 
            else if (element instanceof List) {
                result.addAll(flatten((List<Object>) element));
            }
        }

        return result;
    }

    public static void main(String[] args) {

        List<Object> input = Arrays.asList(
                Arrays.asList(1, 2),
                Arrays.asList(3, Arrays.asList(4, 5))
        );

        System.out.println("Input  : " + input);
        System.out.println("Output : " + flatten(input));
    }
}