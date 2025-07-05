package interview.problems.solved;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 3: Longest Substring Without Repeating Characters
 *
 * Problem Description:
 * ---------------------
 * Given a string s, find the length of the longest substring without repeating characters.
 *
 * Example 1:
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 *
 * Example 2:
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 *
 * Example 3:
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 *
 * Approach:
 * ---------
 * - Use a sliding window with two pointers (left and right).
 * - Use a HashMap to store the last index of each character.
 * - Move the right pointer to expand the window.
 * - If a character is repeated, move the left pointer to the right of the previous occurrence.
 * - Update the maximum length at each step.
 *
 * Complexity:
 * -----------
 * - Time Complexity: O(n), where n is the length of the string.
 * - Space Complexity: O(min(n, m)), where m is the size of the character set.
 */
public class LongestSubstringWithoutRepeating {

    public static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int maxLen = 0;
        int left = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (map.containsKey(c) && map.get(c) >= left) {
                left = map.get(c) + 1;
            }
            map.put(c, right);
            maxLen = Math.max(maxLen, right - left + 1);

        }
        return maxLen;
    }

    public static void main(String[] args) {
        String s1 = "abcdeafghabcbb";
        System.out.println("Longest substring without repeating (Example 1): " + lengthOfLongestSubstring(s1)); // 3

        String s2 = "bbbbb";
        System.out.println("Longest substring without repeating (Example 2): " + lengthOfLongestSubstring(s2)); // 1

        String s3 = "pwwkew";
        System.out.println("Longest substring without repeating (Example 3): " + lengthOfLongestSubstring(s3)); // 3
    }
}