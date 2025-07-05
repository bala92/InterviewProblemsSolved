package interview.problems.solved;

public class LongestPalindromicSubsequence {

    /**
     * Function to find the length of the longest palindromic subsequence.
     *
     * @param s The input string.
     * @return The length of the longest palindromic subsequence.
     */
    public static int longestPalindromeSubseq(String s) {
        int n = s.length();
        // Create a DP table
        int[][] dp = new int[n][n];

        // Base case: Single characters are palindromes of length 1
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        // Fill the DP table for substrings of length 2 and more
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1; // Endpoint of the substring
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        // The result is in dp[0][n-1], which represents the entire string
        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        // Test cases
        String s1 = "bbbab";
        String s2 = "cbbd";

        System.out.println("Longest Palindromic Subsequence of \"" + s1 + "\": " + longestPalindromeSubseq(s1)); // Output: 4
        System.out.println("Longest Palindromic Subsequence of \"" + s2 + "\": " + longestPalindromeSubseq(s2)); // Output: 2
    }
}

/**
 * Explanation of the Code:
 *
 * 1. **DP Table**:
 *    - `dp[i][j]` represents the length of the longest palindromic subsequence in the substring `s[i:j+1]`.
 *
 * 2. **Base Case**:
 *    - If `i == j`, the substring has only one character, so `dp[i][j] = 1`.
 *
 * 3. **Transition**:
 *    - If `s[i] == s[j]`, then `dp[i][j] = dp[i+1][j-1] + 2`.
 *      - This means the characters at `i` and `j` form a palindrome, so we add `2` to the length of the palindrome in the substring `s[i+1:j-1]`.
 *    - If `s[i] != s[j]`, then `dp[i][j] = max(dp[i+1][j], dp[i][j-1])`.
 *      - This means we either skip the character at `i` or the character at `j` and take the maximum result.
 *
 * 4. **Filling the DP Table**:
 *    - Start with substrings of length 2 and build up to the full string.
 *    - Use nested loops to fill the table in a bottom-up manner.
 *
 * Example Walkthrough:
 * --------------------
 * Input:
 * s = "bbbab"
 *
 * Steps:
 * 1. Initialize the DP table:
 *    dp[i][i] = 1 for all `i`.
 * 2. Fill the table for substrings of length 2:
 *    - For `i = 0, j = 1`: `s[0] == s[1]`, so `dp[0][1] = 2`.
 *    - For `i = 1, j = 2`: `s[1] == s[2]`, so `dp[1][2] = 2`.
 *    - For `i = 2, j = 3`: `s[2] == s[3]`, so `dp[2][3] = 2`.
 *    - For `i = 3, j = 4`: `s[3] != s[4]`, so `dp[3][4] = 1`.
 * 3. Fill the table for substrings of length 3, 4, and 5.
 * 4. Result: `dp[0][4] = 4`.
 *
 * Output:
 * Longest Palindromic Subsequence of "bbbab": 4
 *
 * Complexity Analysis:
 * ---------------------
 * 1. **Time Complexity**:
 *    - Filling the DP table requires `O(n^2)` operations, where `n` is the length of the string.
 *
 * 2. **Space Complexity**:
 *    - The DP table uses `O(n^2)` space.
 *
 * Test Cases:
 * -----------
 * Test Case 1:
 * Input: s = "bbbab"
 * Output: 4
 * 
 *   0  1  2  3  4
    +----------------
 0 | 1  2  3  3  4
 1 |    1  2  2  3
 2 |       1  1  2
 3 |          1  1
 4 |             1
 * 
 *
 * Test Case 2:
 * Input: s = "cbbd"
 * Output: 2
 *
 * Test Case 3:
 * Input: s = "a"
 * Output: 1
 *
 * Test Case 4:
 * Input: s = "abcde"
 * Output: 1
 */