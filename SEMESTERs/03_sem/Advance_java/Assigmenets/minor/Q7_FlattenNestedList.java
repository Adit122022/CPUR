import java.util.*;

/**
 * Q7: Flatten a nested list of integers.
 *     Example: [[1,2],[3,[4,5]]] → [1,2,3,4,5]
 *
 * We model the nested structure with a generic Object List (since Java is typed).
 * The recursive helper checks each element:
 *   - If it is an Integer   → add directly to result.
 *   - If it is a List       → recurse into it.
 *
 * Time : O(n) — n = total number of integers across all levels
 * Space: O(d) — d = maximum nesting depth (recursion stack)
 */
public class Q7_FlattenNestedList {

    /**
     * Recursively flattens a mixed List<Object> (may contain Integer or nested List).
     */
    @SuppressWarnings("unchecked")
    public static List<Integer> flatten(List<Object> nested) {
        List<Integer> result = new ArrayList<>();
        for (Object element : nested) {
            if (element instanceof Integer) {
                result.add((Integer) element);
            } else if (element instanceof List) {
                result.addAll(flatten((List<Object>) element));
            }
        }
        return result;
    }

    /**
     * Iterative version using a Deque (stack) — avoids recursion stack overflow
     * for deeply nested input.
     */
    @SuppressWarnings("unchecked")
    public static List<Integer> flattenIterative(List<Object> nested) {
        List<Integer> result = new ArrayList<>();
        Deque<Object> stack  = new ArrayDeque<>();

        // Push in reverse so leftmost element is processed first
        for (int i = nested.size() - 1; i >= 0; i--) {
            stack.push(nested.get(i));
        }

        while (!stack.isEmpty()) {
            Object top = stack.pop();
            if (top instanceof Integer) {
                result.add((Integer) top);
            } else if (top instanceof List) {
                List<Object> inner = (List<Object>) top;
                for (int i = inner.size() - 1; i >= 0; i--) {
                    stack.push(inner.get(i));
                }
            }
        }
        return result;
    }

    // Helper to build nested structure: Arrays.asList() doesn't allow Object mixing cleanly,
    // so we use a small builder.
    private static List<Object> list(Object... items) {
        return new ArrayList<>(Arrays.asList(items));
    }

    public static void main(String[] args) {
        // [[1,2],[3,[4,5]]]
        List<Object> input1 = list(list(1, 2), list(3, list(4, 5)));
        System.out.println("Input      : [[1,2],[3,[4,5]]]");
        System.out.println("Recursive  : " + flatten(input1));
        System.out.println("Iterative  : " + flattenIterative(input1));

        System.out.println();

        // [1,[2,[3,[4,[5]]]]]
        List<Object> input2 = list(1, list(2, list(3, list(4, list(5)))));
        System.out.println("Input      : [1,[2,[3,[4,[5]]]]]");
        System.out.println("Recursive  : " + flatten(input2));
        System.out.println("Iterative  : " + flattenIterative(input2));

        System.out.println();

        // [1,2,3] (already flat)
        List<Object> input3 = list(1, 2, 3);
        System.out.println("Input      : [1,2,3]");
        System.out.println("Recursive  : " + flatten(input3));
        System.out.println("Iterative  : " + flattenIterative(input3));
    }
}
