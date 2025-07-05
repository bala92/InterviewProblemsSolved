package interview.problems.solved;

import java.util.HashMap;

public class WordPattern {

    /**
     * Function to determine if the string follows the given pattern.
     *
     * @param pattern The pattern string.
     * @param s       The input string.
     * @return True if the string follows the pattern, otherwise false.
     */
    public static boolean wordPattern(String pattern, String s) {
        // Split the string into words
        String[] words = s.split(" ");

        // If the number of characters in the pattern and the number of words are different, return false
        if (pattern.length() != words.length) {
            return false;
        }

        // Maps to store the character-to-word and word-to-character mappings
        HashMap<Character, String> charToWord = new HashMap<>();
        HashMap<String, Character> wordToChar = new HashMap<>();

        // Iterate through the pattern and words
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            String word = words[i];

            // Check if the character is already mapped to a word
            if (charToWord.containsKey(c)) {
                // If the mapping is inconsistent, return false
                if (!charToWord.get(c).equals(word)) {
                    return false;
                }
            } else {
                // If the word is already mapped to a different character, return false
                if (wordToChar.containsKey(word)) {
                    return false;
                }

                // Create the mappings
                charToWord.put(c, word);
                wordToChar.put(word, c);
            }
        }

        // If all mappings are consistent, return true
        return true;
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(wordPattern("abba", "dog cat cat dog")); // Output: true
        System.out.println(wordPattern("abba", "dog cat cat fish")); // Output: false
        System.out.println(wordPattern("aaaa", "dog cat cat dog")); // Output: false
        System.out.println(wordPattern("abba", "dog dog dog dog")); // Output: false
    }
}

/**
 * Explanation of the Code:
 *
 * 1. **Splitting the String**:
 *    - The input string `s` is split into words using `split(" ")`.
 *    - If the number of words is not equal to the length of the pattern, return `false`.
 *
 * 2. **Hash Maps**:
 *    - `charToWord`: Maps characters in `pattern` to words in `s`.
 *    - `wordToChar`: Maps words in `s` to characters in `pattern`.
 *
 * 3. **Consistency Check**:
 *    - If a character is already mapped to a word, ensure the mapping is consistent.
 *    - If a word is already mapped to a character, ensure the mapping is consistent.
 *    - If any inconsistency is found, return `false`.
 *
 * 4. **Creating Mappings**:
 *    - If the character and word are not already mapped, create the mappings in both hash maps.
 *
 * Example Walkthrough:
 * --------------------
 * Input:
 * pattern = "abba", s = "dog cat cat dog"
 *
 * Steps:
 * 1. Split `s` into words: `["dog", "cat", "cat", "dog"]`.
 * 2. Initialize `charToWord` and `wordToChar`.
 * 3. Iterate through the pattern and words:
 *    - `'a'` -> `"dog"`: Add to both maps.
 *    - `'b'` -> `"cat"`: Add to both maps.
 *    - `'b'` -> `"cat"`: Mapping is consistent.
 *    - `'a'` -> `"dog"`: Mapping is consistent.
 * 4. Return `true`.
 *
 * Output:
 * true
 *
 * Complexity Analysis:
 * ---------------------
 * 1. **Time Complexity**:
 *    - Splitting the string into words: `O(n)`, where `n` is the length of the string `s`.
 *    - Iterating through the pattern and words: `O(m)`, where `m` is the length of the pattern.
 *    - Overall: `O(n + m)`.
 *
 * 2. **Space Complexity**:
 *    - The hash maps store up to `m` characters and `n` words.
 *    - Space complexity: `O(m + n)`.
 *
 * Test Cases:
 * -----------
 * Test Case 1:
 * Input: pattern = "abba", s = "dog cat cat dog"
 * Output: true
 *
 * Test Case 2:
 * Input: pattern = "abba", s = "dog cat cat fish"
 * Output: false
 *
 * Test Case 3:
 * Input: pattern = "aaaa", s = "dog cat cat dog"
 * Output: false
 *
 * Test Case 4:
 * Input: pattern = "abba", s = "dog dog dog dog"
 * Output: false
 */