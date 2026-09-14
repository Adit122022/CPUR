// 8. Given a list of transactions (userId, amount), write a program to find the user who spent the maximum total amount.
import java.util.*;

/**

Transactions
     ↓
Same user ko group karo
     ↓
Amounts add karo
     ↓
Maximum total find karo
**/

public class Q8_MaxSpender {

    // Transaction class
    static class Transaction {
        String userId;
        double amount;

        Transaction(String userId, double amount) {
            this.userId = userId;
            this.amount = amount;
        }
    }

    public static String findMaxSpender(List<Transaction> transactions) {

        Map<String, Double> totals = new HashMap<>();

        // Store total amount of each user
        for (Transaction t : transactions) {
            totals.put(t.userId, totals.getOrDefault(t.userId, 0.0) + t.amount );
        }

        // Find maximum spender
        String maxUser = "";
        double maxAmount = 0;

        for (Map.Entry<String, Double> entry : totals.entrySet()) {

            if (entry.getValue() > maxAmount) {
                maxAmount = entry.getValue();
                maxUser = entry.getKey();
            }
        }

        return maxUser;
    }

    public static void main(String[] args) {

        List<Transaction> transactions = Arrays.asList(
            new Transaction("Alice", 250),
            new Transaction("Bob", 100),
            new Transaction("Alice", 400),
            new Transaction("Charlie", 800),
            new Transaction("Bob", 350),
            new Transaction("Charlie", 200),
            new Transaction("Alice", 150)
        );

        System.out.println("Max Spender: " +
                findMaxSpender(transactions));
    }
}