import java.util.*;

/**
 * Q8: Given a list of transactions (userId, amount), find the user who spent
 *     the maximum total amount.
 *
 * Approach:
 *  1. Model each transaction as a simple record (userId, amount).
 *  2. Aggregate totals in a HashMap<String, Double>.
 *  3. Find the entry with the maximum value.
 *
 * Time : O(n) — single pass to aggregate + single pass to find max
 * Space: O(u) — u = number of unique users
 */
public class Q8_MaxSpender {

    // ---------- Transaction record ----------
    static class Transaction {
        String userId;
        double amount;

        Transaction(String userId, double amount) {
            this.userId = userId;
            this.amount = amount;
        }

        @Override
        public String toString() {
            return String.format("(%s, $%.2f)", userId, amount);
        }
    }

    // ---------- Core logic ----------
    public static String findMaxSpender(List<Transaction> transactions) {
        if (transactions == null || transactions.isEmpty()) {
            throw new IllegalArgumentException("Transaction list is empty.");
        }

        Map<String, Double> totals = new HashMap<>();
        for (Transaction t : transactions) {
            totals.merge(t.userId, t.amount, Double::sum);
        }

        return Collections.max(totals.entrySet(),
                               Map.Entry.comparingByValue())
                          .getKey();
    }

    public static void main(String[] args) {
        List<Transaction> transactions = Arrays.asList(
            new Transaction("Alice", 250.00),
            new Transaction("Bob",   100.00),
            new Transaction("Alice", 400.00),
            new Transaction("Charlie", 800.00),
            new Transaction("Bob",   350.00),
            new Transaction("Charlie", 200.00),
            new Transaction("Alice", 150.00)
        );

        System.out.println("Transactions:");
        transactions.forEach(t -> System.out.println("  " + t));

        // Print aggregated totals
        Map<String, Double> totals = new HashMap<>();
        for (Transaction t : transactions) {
            totals.merge(t.userId, t.amount, Double::sum);
        }

        System.out.println("\nTotal spending per user:");
        totals.entrySet().stream()
              .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
              .forEach(e -> System.out.printf("  %-10s $%.2f%n", e.getKey(), e.getValue()));

        String winner = findMaxSpender(transactions);
        System.out.printf("%nMax Spender: %s (Total: $%.2f)%n",
                winner, totals.get(winner));
    }
}
