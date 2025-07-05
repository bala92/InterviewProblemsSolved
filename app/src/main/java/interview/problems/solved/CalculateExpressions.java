package interview.problems.solved;

public class CalculateExpressions {

    public static void main(String[] args) {
        // Initial call with the full string, empty expression, starting sum of 0, and target sum of 6
        calculateExpressions("1234", "", 0, 6);

        // Example usage for alternate approach
        generateAllExpressions("1234", 6);
    }

    public static void calculateExpressions(String remainingDigits, String expressionSoFar, int currentSum, int targetSum) {
        // Base case: Check if adding or subtracting the remaining digits achieves the target sum
        int currentNumber = Integer.parseInt(remainingDigits);
        if (currentSum + currentNumber == targetSum) {
            System.out.println(expressionSoFar + " + " + remainingDigits);
        }
        if (currentSum - currentNumber == targetSum) {
            System.out.println(expressionSoFar + " - " + remainingDigits);
        }

        // Recursive case: Split the remaining digits into two parts and explore all combinations
        for (int i = 1; i < remainingDigits.length(); i++) {
            String leftPart = remainingDigits.substring(0, i); // First part of the split
            String rightPart = remainingDigits.substring(i);   // Remaining part of the split

            int leftNumber = Integer.parseInt(leftPart);

            // Recurse by adding the left part to the current sum
            calculateExpressions(
                rightPart,
                expressionSoFar.isEmpty() ? leftPart : expressionSoFar + " + " + leftPart,
                currentSum + leftNumber,
                targetSum
            );

            // Recurse by subtracting the left part from the current sum
            calculateExpressions(
                rightPart,
                expressionSoFar.isEmpty() ? leftPart : expressionSoFar + " - " + leftPart,
                currentSum - leftNumber,
                targetSum
            );
        }
    }

    /**
     * Alternate approach: Generate all possible expressions by inserting '+', '-', or nothing (concatenation) between digits,
     * then evaluate each expression to see if it equals the target sum.
     *
     * This approach builds the expression as a string and only evaluates it when all digits are used.
     *
     * Example: For "123", possible expressions are:
     *   1+2+3, 1+2-3, 1+23, 1-2+3, 1-2-3, 1-23, 12+3, 12-3, 123
     *
     * Time Complexity Analysis for generateAllExpressions:
     *
     * For a string of length n:
     * - At each of the n-1 positions between digits, we can insert '+', '-', or nothing (concatenation).
     * - This gives 3^(n-1) possible expressions to generate and evaluate.
     * - For each expression, evaluating the sum is O(n) (since we build the sum as we go).
     *
     * Therefore, the total time complexity is O(3^n), dominated by the number of possible expressions.
     *
     * Space Complexity:
     * - O(n) for the recursion stack and building expressions.
     */
    public static void generateAllExpressions(String digits, int targetSum) {
        helper(digits, 0, "", 0, targetSum);
    }

    // pos: current position in digits
    // expr: expression built so far
    // sum: cumulative sum so far
    private static void helper(String digits, int pos, String expr, long sum, int target) {
        if (pos == digits.length()) {
            if (sum == target) {
                System.out.println(expr);
            }
            return;
        }
        for (int i = pos; i < digits.length(); i++) {
            String part = digits.substring(pos, i + 1);
            long curr = Long.parseLong(part);
            if (pos == 0) {
                // First number, no operator
                helper(digits, i + 1, part, curr, target);
            } else {
                // Add '+'
                helper(digits, i + 1, expr + "+" + part, sum + curr, target);
                // Add '-'
                helper(digits, i + 1, expr + "-" + part, sum - curr, target);
            }
        }
    }

}
