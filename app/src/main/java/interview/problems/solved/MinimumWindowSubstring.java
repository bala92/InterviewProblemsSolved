package interview.problems.solved;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {

    /**
     * Finds the minimum window substring in s that contains all characters of t.
     *
     * @param s The input string.
     * @param t The target string.
     * @return The minimum window substring, or an empty string if no such window exists.
     */
    public static String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }

        // Step 1: Count characters in t
        Map<Character, Integer> tFreq = new HashMap<>();
        for (char c : t.toCharArray()) {
            tFreq.put(c, tFreq.getOrDefault(c, 0) + 1);
        }

        // Step 2: Sliding window variables
        Map<Character, Integer> windowFreq = new HashMap<>();
        int left = 0, right = 0;
        int required = tFreq.size(); // Number of unique characters in t
        int formed = 0; // Number of unique characters in the current window that match t
        int[] result = {-1, 0, 0}; // {window length, left, right}

        // Step 3: Expand the window
        while (right < s.length()) {
            char c = s.charAt(right);
            windowFreq.put(c, windowFreq.getOrDefault(c, 0) + 1);

            // Check if the current character satisfies the frequency in t
            if (tFreq.containsKey(c) && windowFreq.get(c).intValue() == tFreq.get(c).intValue()) {
                formed++;
            }

            // Step 4: Shrink the window
            while (left <= right && formed == required) {
                c = s.charAt(left);

                // Update the result if this window is smaller
                if (result[0] == -1 || right - left + 1 < result[0]) {
                    result[0] = right - left + 1;
                    result[1] = left;
                    result[2] = right;
                }

                // Remove the left character from the window
                windowFreq.put(c, windowFreq.get(c) - 1);
                if (tFreq.containsKey(c) && windowFreq.get(c).intValue() < tFreq.get(c).intValue()) {
                    formed--;
                }

                left++;
            }

            // Expand the window
            right++;
        }

        // Step 5: Return the result
        return result[0] == -1 ? "" : s.substring(result[1], result[2] + 1);
    }

    public static void main(String[] args) {
        // Example 1
        String s1 = "ADOBECODEBANC";
        String t1 = "ABC";
        System.out.println("Minimum Window Substring (Example 1): " + minWindow(s1, t1)); // Output: "BANC"

        // Example 2
        String s2 = "a";
        String t2 = "a";
        System.out.println("Minimum Window Substring (Example 2): " + minWindow(s2, t2)); // Output: "a"

        // Example 3
        String s3 = "a";
        String t3 = "aa";
        System.out.println("Minimum Window Substring (Example 3): " + minWindow(s3, t3)); // Output: ""
    }
}

/**
 * Explanation:
 *
 * Approach:
 * - Use a sliding window to find the minimum substring in s that contains all characters of t.
 * - Expand the window by moving the right pointer and include characters in the window.
 * - Shrink the window by moving the left pointer when all characters in t are included.
 * - Keep track of the minimum window that satisfies the condition.
 *
 * Example Walkthrough:
 * Input: s = "ADOBECODEBANC", t = "ABC"
 * - Step 1: Count characters in t: {A: 1, B: 1, C: 1}.
 * - Step 2: Expand the window:
 *   - Right = 0, Add 'A': Window = {A: 1}, Formed = 1.
 *   - Right = 1, Add 'D': Window = {A: 1, D: 1}, Formed = 1.
 *   - Right = 2, Add 'O': Window = {A: 1, D: 1, O: 1}, Formed = 1.
 *   - Right = 3, Add 'B': Window = {A: 1, D: 1, O: 1, B: 1}, Formed = 2.
 *   - Right = 4, Add 'E': Window = {A: 1, D: 1, O: 1, B: 1, E: 1}, Formed = 2.
 *   - Right = 5, Add 'C': Window = {A: 1, D: 1, O: 1, B: 1, E: 1, C: 1}, Formed = 3.
 * - Step 3: Shrink the window:
 *   - Left = 0, Remove 'A': Window = {A: 0, D: 1, O: 1, B: 1, E: 1, C: 1}, Formed = 2.
 * - Continue expanding and shrinking until the minimum window is found.
 * Output: "BANC".
 *
 * Complexity:
 * - Time Complexity: O(N + M), where N is the length of s and M is the length of t.
 * - Space Complexity: O(M), for storing the frequency map of t.
 */