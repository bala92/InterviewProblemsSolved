package interview.problems.solved;

public class OneEditDistance {

    /**
     * Function to determine if two strings are exactly one edit distance apart.
     *
     * @param s The first string.
     * @param t The second string.
     * @return True if the strings are one edit distance apart, otherwise false.
     */
    public static boolean isOneEditDistance(String s, String t) {
        int m = s.length();
        int n = t.length();

        // If the length difference is greater than 1, they cannot be one edit apart
        if (Math.abs(m - n) > 1) {
            return false;
        }

        // Use two pointers to compare the strings
        int i = 0, j = 0;
        boolean foundDifference = false;

        while (i < m && j < n) {
            if (s.charAt(i) != t.charAt(j)) {
                // If a difference is already found, return false
                if (foundDifference) {
                    return false;
                }
                foundDifference = true;

                // Handle the three possible edits
                if (m > n) {
                    // Delete a character from s
                    i++;
                } else if (m < n) {
                    // Insert a character into s
                    j++;
                } else {
                    // Replace a character in s
                    i++;
                    j++;
                }
            } else {
                // If characters match, move both pointers
                i++;
                j++;
            }
        }

        // Handle the case where the last character is extra in one of the strings
        if (i < m || j < n) {
            foundDifference = true;
        }

        return foundDifference;
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(isOneEditDistance("abc", "ab"));    // true
        System.out.println(isOneEditDistance("abc", "adc"));   // true
        System.out.println(isOneEditDistance("abc", "abcd"));  // true
        System.out.println(isOneEditDistance("abc", "abc"));   // false
        System.out.println(isOneEditDistance("", "a"));        // true
        System.out.println(isOneEditDistance("a", ""));        // true
        System.out.println(isOneEditDistance("abc", "def"));   // false
    }
}

/**
 * Explanation of the Code:
 *
 * 1. **Length Difference Check**:
 *    - If the length difference between `s` and `t` is greater than 1, return `false` immediately.
 *    - This is because more than one edit would be required.
 *
 * 2. **Two Pointers**:
 *    - Use two pointers `i` and `j` to traverse the strings `s` and `t`.
 *    - Compare characters at `s[i]` and `t[j]`.
 *
 * 3. **Handling Mismatches**:
 *    - If a mismatch is found:
 *      - If the strings are the same length, simulate replacing a character by moving both pointers.
 *      - If one string is longer, simulate deleting or inserting a character by moving the pointer of the longer string.
 *    - If a second mismatch is found, return `false`.
 *
 * 4. **Edge Case for Extra Characters**:
 *    - After the loop, if one string has an extra character at the end, it is still valid as one edit.
 *
 * Example Walkthrough:
 * --------------------
 * Input:
 * s = "abc", t = "adc"
 *
 * Steps:
 * 1. Compare `s[0]` and `t[0]`: `'a' == 'a'`, move both pointers.
 * 2. Compare `s[1]` and `t[1]`: `'b' != 'd'`, mark `foundDifference = true`, move both pointers.
 * 3. Compare `s[2]` and `t[2]`: `'c' == 'c'`, move both pointers.
 * 4. End of strings, return `true`.
 *
 * Output:
 * true
 *
 * Complexity Analysis:
 * ---------------------
 * 1. **Time Complexity**:
 *    - The strings are traversed once, so the time complexity is `O(n)`, where `n` is the length of the shorter string.
 *
 * 2. **Space Complexity**:
 *    - The algorithm uses constant space, so the space complexity is `O(1)`.
 *
 * Test Cases:
 * -----------
 * Test Case 1:
 * Input: s = "abc", t = "ab"
 * Output: true
 *
 * Test Case 2:
 * Input: s = "abc", t = "adc"
 * Output: true
 *
 * Test Case 3:
 * Input: s = "abc", t = "abcd"
 * Output: true
 *
 * Test Case 4:
 * Input: s = "abc", t = "abc"
 * Output: false
 *
 * Test Case 5:
 * Input: s = "", t = "a"
 * Output: true
 *
 * Test Case 6:
 * Input: s = "abc", t = "def"
 * Output: false
 */