package interview.problems.solved;

import java.util.*;

public class MinimumRemoveToMakeValid {

    /**
     * Removes the minimum number of parentheses to make the string valid.
     *
     * @param s The input string.
     * @return The resulting valid string.
     */
    public static String minRemoveToMakeValid(String s) {
        Stack<Integer> stack = new Stack<>();
        Set<Integer> invalidIndices = new HashSet<>();

        // First pass: Identify invalid parentheses
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else if (c == ')') {
                if (stack.isEmpty()) {
                    invalidIndices.add(i); // Unmatched closing parenthesis
                } else {
                    stack.pop(); // Match found
                }
            }
        }

        // Add remaining unmatched opening parentheses to the invalid set
        while (!stack.isEmpty()) {
            invalidIndices.add(stack.pop());
        }

        // Second pass: Build the valid string
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!invalidIndices.contains(i)) {
                result.append(s.charAt(i));
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        // Example 1
        String s1 = "lee(t(c)o)de)";
        System.out.println("Result: " + minRemoveToMakeValid(s1)); // Output: "lee(t(c)o)de"

        // Example 2
        String s2 = "a)b(c)d";
        System.out.println("Result: " + minRemoveToMakeValid(s2)); // Output: "ab(c)d"

        // Example 3
        String s3 = "))((";
        System.out.println("Result: " + minRemoveToMakeValid(s3)); // Output: ""
    }
}