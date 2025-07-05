package interview.problems.solved;

/**
 * Problem Description:
 * ---------------------
 * Check if a string can be constructed by repeating a substring multiple times.
 *
 * Approach:
 * ---------
 * 1. Iterate over all possible lengths of the repeating substring.
 * 2. For each valid length, check if the substring can reconstruct the original string.
 *
 * Complexity:
 * -----------
 * - Time Complexity: O(n^2), where n is the length of the string.
 * - Space Complexity: O(n), for the temporary substring.
 */
public class RepeatedSubstringPattern {

    /**
     * Function to check if a string can be constructed by repeating a substring.
     *
     * @param s The input string.
     * @return True if the string can be constructed by repeating a substring, otherwise false.
     */
    public boolean repeatedSubstringPattern(String s) {
        int n = s.length();

        // Iterate over all possible lengths of the repeating substring
        for (int len = 1; len <= n / 2; len++) {
            // Check if the length divides the string evenly
            if (n % len == 0) {
                String substring = s.substring(0, len); // Get the potential repeating substring
                StringBuilder repeated = new StringBuilder();

                // Repeat the substring n / len times
                for (int i = 0; i < n / len; i++) {
                    repeated.append(substring);
                }

                // Check if the repeated string matches the original string
                if (repeated.toString().equals(s)) {
                    return true;
                }
            }
        }

        return false; // No valid repeating substring found
    }

    public static void main(String[] args) {
        RepeatedSubstringPattern solution = new RepeatedSubstringPattern();

        // Example 1
        String s1 = "abab";
        System.out.println("Is repeated substring pattern (Example 1): " + solution.repeatedSubstringPattern(s1));
        // Output: true (substring "ab" repeated twice)

        // Example 2
        String s2 = "aba";
        System.out.println("Is repeated substring pattern (Example 2): " + solution.repeatedSubstringPattern(s2));
        // Output: false

        // Example 3
        String s3 = "abcabcabcabc";
        System.out.println("Is repeated substring pattern (Example 3): " + solution.repeatedSubstringPattern(s3));
        // Output: true (substring "abc" repeated four times)
    }
}