package interview.problems.solved;

import java.util.*;

/**
 * LeetCode 402: Remove K Digits
 *
 * Problem:
 * Given a non-negative integer num represented as a string and an integer k,
 * remove k digits from the number so that the new number is the smallest possible.
 *
 * Note:
 * - The result should not contain leading zeros unless the result is 0.
 *
 * Example:
 * Input: num = "1432219", k = 3
 * Output: "1219"
 *
 * Input: num = "10200", k = 1
 * Output: "200"
 *
 * Input: num = "10", k = 2
 * Output: "0"
 *
 * Approach:
 * - Use a stack to build the smallest number by removing digits greedily.
 * - For each digit, remove the previous digit from the stack if it is greater than the current digit and k > 0.
 * - After processing all digits, remove any remaining digits from the end if k > 0.
 * - Build the result from the stack and remove leading zeros.
 *
 * Time Complexity: O(n), where n is the length of num. Each digit is pushed and popped at most once.
 * Space Complexity: O(n) for the stack.
 */
public class RemoveKDigits {
    public String removeKdigits(String num, int k) {
        int n = num.length();
        if (k == n) return "0";
        Stack<Character> stack = new Stack<>();
        for (char digit : num.toCharArray()) {
            while (!stack.isEmpty() && k > 0 && stack.peek() > digit) {
                stack.pop();
                k--;
            }
            stack.add(digit);
        }
        // Remove remaining digits from the end
        while (k > 0) {
            stack.pop();
            k--;
        }
        // Build the result and remove leading zeros
        StringBuilder sb = new StringBuilder();
        boolean leadingZero = true;
        for (char digit : stack) {
            if (leadingZero && digit == '0') continue;
            leadingZero = false;
            sb.append(digit);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }

    // Example usage
    public static void main(String[] args) {
        RemoveKDigits solver = new RemoveKDigits();
        System.out.println(solver.removeKdigits("1432219", 3)); // Output: "1219"
        System.out.println(solver.removeKdigits("10200", 1));   // Output: "200"
        System.out.println(solver.removeKdigits("10", 2));      // Output: "0"
    }
}
