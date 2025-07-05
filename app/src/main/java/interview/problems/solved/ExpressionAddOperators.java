package interview.problems.solved;

import java.util.ArrayList;
import java.util.List;

public class ExpressionAddOperators {

    /**
     * Finds all expressions that evaluate to the target value.
     *
     * @param num    The input string containing digits.
     * @param target The target value.
     * @return A list of valid expressions.
     */
    public static List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<>();
        if (num == null || num.length() == 0) {
            return result;
        }
        backtrack(result, num, target, "", 0, 0, 0);
        return result;
    }

    /**
     * Backtracking helper method to explore all possible expressions.
     *
     * @param result      The list of valid expressions.
     * @param num         The input string.
     * @param target      The target value.
     * @param expression  The current expression being built.
     * @param index       The current index in the input string.
     * @param currentValue The current value of the expression.
     * @param lastOperand The last operand used (for handling multiplication).
     */
    private static void backtrack(List<String> result, String num, int target, String expression, int index, long currentValue, long lastOperand) {
        // Base case: If we've processed all digits
        if (index == num.length()) {
            if (currentValue == target) {
                result.add(expression);
            }
            return;
        }

        // Try all possible splits of the remaining string
        for (int i = index; i < num.length(); i++) {
            // Skip numbers with leading zeros
            if (i != index && num.charAt(index) == '0') {
                break;
            }

            // Extract the current number
            String currentStr = num.substring(index, i + 1);
            long currentNum = Long.parseLong(currentStr);

            // If this is the first number, it must be added without an operator
            if (index == 0) {
                backtrack(result, num, target, currentStr, i + 1, currentNum, currentNum);
            } else {
                // Add '+'
                backtrack(result, num, target, expression + "+" + currentStr, i + 1, currentValue + currentNum, currentNum);

                // Add '-'
                backtrack(result, num, target, expression + "-" + currentStr, i + 1, currentValue - currentNum, -currentNum);

                // Add '*'
                backtrack(result, num, target, expression + "*" + currentStr, i + 1, currentValue - lastOperand + lastOperand * currentNum, lastOperand * currentNum);
            }
        }
    }

    /**
     * Time Complexity Analysis:
     *
     * For an input string of length n:
     * - At each of the n-1 positions between digits, we can insert '+', '-', '*', or nothing (concatenation).
     * - This gives up to 4^(n-1) possible expressions to generate and evaluate.
     * - For each expression, evaluating the sum is O(n) (since we build the sum as we go in the recursion).
     *
     * Therefore, the total time complexity is O(4^n), dominated by the number of possible expressions.
     *
     * Space Complexity:
     * - O(n) for the recursion stack and building expressions.
     */
    public static void main(String[] args) {
        // Example usage
        String num = "123456";
        int target = 6;
        List<String> result = addOperators(num, target);
        System.out.println("Expressions that evaluate to " + target + ": " + result);

        num = "232";
        target = 8;
        result = addOperators(num, target);
        System.out.println("Expressions that evaluate to " + target + ": " + result);
    }
}