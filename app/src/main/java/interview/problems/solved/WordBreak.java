package interview.problems.solved;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak {

    /**
     * Function to determine if the string can be segmented into words from the dictionary.
     *
     * @param s        The input string.
     * @param wordDict The list of words in the dictionary.
     * @return True if the string can be segmented, otherwise false.
     */
    public static boolean wordBreak(String s, List<String> wordDict) {
        // Convert the word dictionary to a HashSet for faster lookups
        Set<String> wordSet = new HashSet<>(wordDict);

        // Create a DP array to store whether each substring can be segmented
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true; // Base case: an empty string can always be segmented

        // Iterate through the string
        for (int i = 1; i <= s.length(); i++) {
            // Check all substrings ending at position i
            for (int j = 0; j < i; j++) {
                // If the substring s[j:i] is in the dictionary and dp[j] is true
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break; // No need to check further
                }
            }
        }

        // The result is stored in dp[s.length()]
        return dp[s.length()];
    }

    public static void main(String[] args) {
        // Example input
        String s = "leetcode";
        List<String> wordDict = List.of("leet", "code");

        // Call the wordBreak function
        boolean result = wordBreak(s, wordDict);

        // Print the result
        System.out.println("Can the string \"" + s + "\" be segmented? " + result);
    }
}

/**
 * Explanation of the Code:
 *
 * 1. **Dynamic Programming Array**:
 *    - `dp[i]` represents whether the substring `s[0:i]` can be segmented into words from the dictionary.
 *    - `dp[0] = true` because an empty string can always be segmented.
 *
 * 2. **Nested Loops**:
 *    - Outer loop iterates over the string from `1` to `s.length()`.
 *    - Inner loop checks all substrings `s[j:i]` (where `0 <= j < i`).
 *    - If `dp[j]` is `true` and `s[j:i]` is in the dictionary, set `dp[i] = true`.
 *
 * 3. **HashSet for Fast Lookups**:
 *    - The word dictionary is converted to a `HashSet` for `O(1)` lookups.
 *
 * Example Walkthrough:
 * --------------------
 * Input:
 * s = "leetcode"
 * wordDict = ["leet", "code"]
 *
 * Steps:
 * 1. Initialize `dp` array: `dp = [true, false, false, false, false, false, false, false, false]`.
 * 2. Check substrings:
 *    - For `i = 4`: `s[0:4] = "leet"` is in the dictionary, so `dp[4] = true`.
 *    - For `i = 8`: `s[4:8] = "code"` is in the dictionary, so `dp[8] = true`.
 * 3. Final `dp` array: `dp = [true, false, false, false, true, false, false, false, true]`.
 * 4. Result: `dp[s.length()] = dp[8] = true`.
 *
 * Complexity Analysis:
 * ---------------------
 * 1. **Time Complexity**:
 *    - Outer loop runs `O(n)` times (where `n` is the length of the string).
 *    - Inner loop runs `O(n)` times in the worst case.
 *    - Checking substrings and HashSet lookups are `O(1)`.
 *    - Overall: `O(n^2)`.
 *
 * 2. **Space Complexity**:
 *    - The `dp` array uses `O(n)` space.
 *    - The `HashSet` uses `O(m)` space (where `m` is the number of words in the dictionary).
 *    - Overall: `O(n + m)`.
 *
 * Test Cases:
 * -----------
 * Test Case 1:
 * Input: s = "leetcode", wordDict = ["leet", "code"]
 * Output: true
 *
 * Test Case 2:
 * Input: s = "applepenapple", wordDict = ["apple", "pen"]
 * Output: true
 *
 * Test Case 3:
 * Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output: false
 */