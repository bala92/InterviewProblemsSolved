package interview.problems.solved;

public class MinimumAddToMakeValid {

    /**
     * Calculates the minimum number of parentheses to add to make the string valid.
     *
     * @param s The input string of parentheses.
     * @return The minimum number of parentheses to add.
     */
    public static int minAddToMakeValid(String s) {
        int balance = 0; // Tracks unmatched '('
        int additions = 0; // Tracks unmatched ')'

        // Traverse the string
        for (char c : s.toCharArray()) {
            if (c == '(') {
                balance++; // Increment balance for an unmatched '('
            } else if (c == ')') {
                if (balance > 0) {
                    balance--; // Match a '(' with this ')'
                } else {
                    additions++; // Increment additions for an unmatched ')'
                }
            }
        }

        // Total additions needed = unmatched '(' + unmatched ')'
        return balance + additions;
    }

    public static void main(String[] args) {
        // Example 1
        String s1 = "())";
        System.out.println("Minimum Additions: " + minAddToMakeValid(s1)); // Output: 1

        // Example 2
        String s2 = "(((";
        System.out.println("Minimum Additions: " + minAddToMakeValid(s2)); // Output: 3

        // Example 3
        String s3 = "()";
        System.out.println("Minimum Additions: " + minAddToMakeValid(s3)); // Output: 0

        // Example 4
        String s4 = "()))((";
        System.out.println("Minimum Additions: " + minAddToMakeValid(s4)); // Output: 4
    }
}

/**
 * Explanation:
 *
 * Approach:
 * - Use two counters:
 *   1. `balance`: Tracks unmatched opening parentheses `'('`.
 *   2. `additions`: Tracks unmatched closing parentheses `')'`.
 * - Traverse the string:
 *   - For each `'('`, increment `balance`.
 *   - For each `')'`:
 *     - If `balance > 0`, decrement `balance` (indicating a match for an opening parenthesis).
 *     - Otherwise, increment `additions` (indicating an unmatched closing parenthesis).
 * - The total number of parentheses to add is `balance + additions`.
 *
 * Example Walkthrough:
 * Input: "()))(("
 * - Step 1: '(' → balance = 1
 * - Step 2: '(' → balance = 2
 * - Step 3: ')' → balance = 1
 * - Step 4: ')' → balance = 0
 * - Step 5: ')' → additions = 1
 * - Step 6: '(' → balance = 1
 * - Step 7: '(' → balance = 2
 * Output: balance + additions = 2 + 2 = 4
 *
 * Complexity:
 * - Time Complexity: O(n), where n is the length of the string.
 * - Space Complexity: O(1), as we use only two counters.
 */