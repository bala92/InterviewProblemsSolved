package interview.problems.solved;

import java.util.Stack;

public class BasicCalculatorII {

    /**
     * Evaluates a basic mathematical expression containing +, -, *, / operators.
     *
     * @param s The input string expression.
     * @return The result of the evaluation.
     */
    public static int calculate(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        Stack<Integer> stack = new Stack<>();
        int currentNumber = 0;
        char operation = '+'; // Default operation is addition

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // If the character is a digit, build the current number
            if (Character.isDigit(c)) {
                currentNumber = currentNumber * 10 + (c - '0');
            }

            // If the character is an operator or the end of the string
            if (!Character.isDigit(c) && c != ' ' || i == s.length() - 1) {
                switch (operation) {
                    case '+':
                        stack.push(currentNumber);
                        break;
                    case '-':
                        stack.push(-currentNumber);
                        break;
                    case '*':
                        stack.push(stack.pop() * currentNumber);
                        break;
                    case '/':
                        stack.push(stack.pop() / currentNumber);
                        break;
                }

                // Reset currentNumber and update the operation
                currentNumber = 0;
                operation = c;
            }
        }

        // Sum up all the values in the stack
        int result = 0;
        while (!stack.isEmpty()) {
            result += stack.pop();
        }

        return result;
    }

    public static void main(String[] args) {
        // Example 1
        String expression1 = "3+2*2";
        System.out.println("Result (Example 1): " + calculate(expression1)); // Output: 7

        // Example 2
        String expression2 = " 3/2 ";
        System.out.println("Result (Example 2): " + calculate(expression2)); // Output: 1

        // Example 3
        String expression3 = " 3+5 / 2 ";
        System.out.println("Result (Example 3): " + calculate(expression3)); // Output: 5
    }
}