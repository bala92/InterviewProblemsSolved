package interview.problems.solved;

/**
 * LeetCode 5: Longest Palindromic Substring
 *
 * Problem Description:
 * ---------------------
 * Given a string s, return the longest palindromic substring in s.
 *
 * Examples:
 * ---------
 * Example 1:
 * Input: s = "babad"
 * Output: "bab"
 * Explanation: "aba" is also a valid answer.
 *
 * Example 2:
 * Input: s = "cbbd"
 * Output: "bb"
 *
 * Example 3:
 * Input: s = "a"
 * Output: "a"
 *
 * Example 4:
 * Input: s = "ac"
 * Output: "a"
 *
 * Approach:
 * ---------
 * - Use the "Expand Around Center" approach.
 * - A palindrome mirrors around its center.
 * - There are 2n - 1 centers in a string of length n (each character and the gaps between characters).
 * - For each center, expand outward while the substring remains a palindrome.
 * - Keep track of the longest palindrome found.
 *
 * Complexity:
 * -----------
 * - Time Complexity: O(n²)
 *   - For each character, we expand outward, which takes O(n) time.
 *   - There are O(n) centers, so the total complexity is O(n²).
 * - Space Complexity: O(1)
 *   - No additional space is used apart from a few variables.
 */

public class LongestPalindromeSubstring {

    /**
     * Finds the longest palindromic substring in the given string.
     *
     * @param s The input string.
     * @return The longest palindromic substring.
     */
    public static String longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }

        int start = 0, end = 0;

        for (int i = 0; i < s.length(); i++) {
            // Expand around single character center
            int len1 = expandAroundCenter(s, i, i);
            // Expand around two-character center
            int len2 = expandAroundCenter(s, i, i + 1);
            // Get the maximum length from both cases
            int len = Math.max(len1, len2);

            // Update the start and end indices of the longest palindrome
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }

        return s.substring(start, end + 1);
    }

    /**
     * Expands around the given center and calculates the length of the palindrome.
     *
     * @param s     The input string.
     * @param left  The left index of the center.
     * @param right The right index of the center.
     * @return The length of the palindrome.
     */
    private static int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1; // Length of the palindrome
    }

    public static void main(String[] args) {
        String s1 = "babad";
        System.out.println("Longest Palindromic Substring (Example 1): " + longestPalindrome(s1)); // Output: "bab" or "aba"

        String s2 = "cbbd";
        System.out.println("Longest Palindromic Substring (Example 2): " + longestPalindrome(s2)); // Output: "bb"

        String s3 = "a";
        System.out.println("Longest Palindromic Substring (Example 3): " + longestPalindrome(s3)); // Output: "a"

        String s4 = "ac";
        System.out.println("Longest Palindromic Substring (Example 4): " + longestPalindrome(s4)); // Output: "a" or "c"
    }
}