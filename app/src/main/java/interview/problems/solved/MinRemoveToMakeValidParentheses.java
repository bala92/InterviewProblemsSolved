package interview.problems.solved;

/**
 * LeetCode 1249: Minimum Remove to Make Valid Parentheses
 *
 * Problem:
 * Given a string s of '(' , ')' and lowercase English characters, remove the minimum number of parentheses
 * so that the resulting string is valid. Return any valid string.
 *
 * A string is valid if it is empty, contains only lowercase characters, or:
 * - It can be written as AB (A concatenated with B), where A and B are valid strings, or
 * - It can be written as (A), where A is a valid string.
 *
 * Example:
 * Input: s = "lee(t(c)o)de)"
 * Output: "lee(t(c)o)de"
 *
 * Approach:
 * - Use a two-pass approach:
 *   1. First pass: Remove extra closing parentheses ')'.
 *   2. Second pass (reverse): Remove extra opening parentheses '('.
 * - Build the result using a StringBuilder for efficiency.
 *
 * Time Complexity: O(n), where n is the length of s (two passes).
 * Space Complexity: O(n), for the result string.
 */
public class MinRemoveToMakeValidParentheses {
    public String minRemoveToMakeValid(String s) {
        StringBuilder sb = new StringBuilder();
        int open = 0;
        // First pass: remove extra ')'
        for (char c : s.toCharArray()) {
            if (c == '(') {
                open++;
                sb.append(c);
            } else if (c == ')') {
                if (open > 0) {
                    open--;
                    sb.append(c);
                }
                // else skip this ')'
            } else {
                sb.append(c);
            }
        }
        // Second pass: remove extra '('  traverse tthe array in reverse order
        // and keep track of how many ')' we have seen
        StringBuilder result = new StringBuilder();
        int close = 0;
        for (int i = sb.length() - 1; i >= 0; i--) {
            char c = sb.charAt(i);
            if (c == ')') {
                close++;
                result.append(c);
            } else if (c == '(') {
                if (close > 0) {
                    close--;
                    result.append(c);
                }
                // else skip this '('
            } else {
                result.append(c);
            }
        }
        return result.reverse().toString();
    }

    // Example usage
    public static void main(String[] args) {
        MinRemoveToMakeValidParentheses solver = new MinRemoveToMakeValidParentheses();
        String s1 = "lee(t(c)o)de)";
        String s2 = "a)b(c)d";
        String s3 = ")a(b(c)d";
        System.out.println("Valid string 1: " + solver.minRemoveToMakeValid(s1)); // Output: "lee(t(c)o)de"
        System.out.println("Valid string 2: " + solver.minRemoveToMakeValid(s2)); // Output: "ab(c)d"
        System.out.println("Valid string 3: " + solver.minRemoveToMakeValid(s3)); // Output: "a(b(c)d"
    }
}
