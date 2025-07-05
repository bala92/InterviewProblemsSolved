package interview.problems.solved;

/**
 * LeetCode 91: Decode Ways
 *
 * Problem Description:
 * ---------------------
 * A message containing letters from A-Z can be encoded into numbers using the following mapping:
 * 'A' -> "1", 'B' -> "2", ..., 'Z' -> "26"
 * Given a string s containing only digits, return the number of ways to decode it.
 *
 * Examples:
 * ---------
 * Example 1:
 * Input: s = "12"
 * Output: 2
 * Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).
 *
 * Example 2:
 * Input: s = "226"
 * Output: 3
 * Explanation: "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 *
 * Example 3:
 * Input: s = "06"
 * Output: 0
 * Explanation: "06" cannot be decoded because '0' is not mapped to any letter.
 *
 * Approach:
 * ---------
 * - Use dynamic programming (DP) to solve the problem.
 * - Let dp[i] be the number of ways to decode the substring s[0..i-1].
 * - For each position i:
 *   - If s[i-1] is not '0', add dp[i-1] (single digit decode).
 *   - If the two-digit number s[i-2..i-1] is between 10 and 26, add dp[i-2] (two digit decode).
 * - dp[0] = 1 (empty string has one way to decode).
 *
 * Complexity:
 * -----------
 * - Time Complexity: O(n), where n is the length of the string.
 * - Space Complexity: O(n), can be optimized to O(1).
 */

public class DecodeWays {

    public static int numDecodings(String s) {
        if (s == null || s.length() == 0) return 0;

        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1; // Empty string

        // dp[1] depends on the first character
        dp[1] = s.charAt(0) != '0' ? 1 : 0;

        for (int i = 2; i <= n; i++) {
            // Single digit decode (if not '0')
            if (s.charAt(i - 1) != '0') {
                dp[i] += dp[i - 1];
            }
            // Two digit decode (if valid between 10 and 26)
            int twoDigit = Integer.parseInt(s.substring(i - 2, i));
            if (twoDigit >= 10 && twoDigit <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        String s1 = "12";
        System.out.println("Decode Ways (Example 1): " + numDecodings(s1)); // Output: 2

        String s2 = "226";
        System.out.println("Decode Ways (Example 2): " + numDecodings(s2)); // Output: 3

        String s3 = "06";
        System.out.println("Decode Ways (Example 3): " + numDecodings(s3)); // Output: 0
    }
}