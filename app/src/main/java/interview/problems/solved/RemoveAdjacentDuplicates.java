package interview.problems.solved;

import java.util.Stack;

public class RemoveAdjacentDuplicates {

    /**
     * Removes adjacent duplicate characters from the string.
     *
     * @param s The input string.
     * @return The resulting string after removing adjacent duplicates.
     */
    public static String removeDuplicates(String s) {
        Stack<Character> stack = new Stack<>();

        // Traverse the string
        for (char c : s.toCharArray()) {
            // If the stack is not empty and the top matches the current character, pop it
            if (!stack.isEmpty() && stack.peek() == c) {
                stack.pop();
            } else {
                // Otherwise, push the current character onto the stack
                stack.push(c);
            }
        }

        // Build the resulting string from the stack
        StringBuilder result = new StringBuilder();
        for (char c : stack) {
            result.append(c);
        }      

        return result.toString();
    }

    public static void main(String[] args) {
        // Example usage
        String input = "abbaca";
        String result = removeDuplicates(input);
        System.out.println("Input: " + input);
        System.out.println("Output: " + result); // Output: "ca"

        input = "azxxzy";
        result = removeDuplicates(input);
        System.out.println("Input: " + input);
        System.out.println("Output: " + result); // Output: "ay"
    }
}