package interview.problems.solved;

public class ValidWordAbbreviation {

    /**
     * Checks if the given abbreviation is valid for the given word.
     *
     * Explanation:
     * - We use two pointers: one for the word (wordIndex) and one for the abbreviation (abbrIndex).
     * - Traverse both strings:
     *   - If the current abbreviation character is a digit:
     *     - If it's '0', return false (leading zeros are not allowed).
     *     - Parse the full number (could be multiple digits) and skip that many characters in the word.
     *   - If it's a letter:
     *     - It must match the current character in the word. If not, return false.
     *     - Move both pointers forward.
     * - At the end, both pointers must reach the end of their respective strings for the abbreviation to be valid.
     *
     * Example:
     *   word = "internationalization", abbr = "i12iz4n"
     *   - 'i' matches 'i'
     *   - '12' means skip 12 characters: "nternationaliz" (now at 'i')
     *   - 'i' matches 'i'
     *   - 'z' matches 'z'
     *   - '4' means skip 4 characters: "atio" (now at 'n')
     *   - 'n' matches 'n'
     *   - Both pointers reach the end, so it's valid.
     *
     * Time Complexity: O(N), where N is the length of abbr (since each character is processed once).
     * Space Complexity: O(1).
     *
     * @param word The original word.
     * @param abbr The abbreviation to validate.
     * @return True if the abbreviation is valid, false otherwise.
     */
    public static boolean validWordAbbreviation(String word, String abbr) {
        int wordIndex = 0, abbrIndex = 0;

        while (wordIndex < word.length() && abbrIndex < abbr.length()) {
            char abbrChar = abbr.charAt(abbrIndex);

            // If the abbreviation character is a digit
            if (Character.isDigit(abbrChar)) {
                // Leading zeros are invalid
                if (abbrChar == '0') {
                    return false;
                }

                // Parse the number in the abbreviation
                int num = 0;
                while (abbrIndex < abbr.length() && Character.isDigit(abbr.charAt(abbrIndex))) {
                    num = num * 10 + (abbr.charAt(abbrIndex) - '0');
                    abbrIndex++;
                }

                // Skip the corresponding number of characters in the word
                wordIndex += num;
            } else {
                // If the characters don't match, return false
                if (wordIndex >= word.length() || word.charAt(wordIndex) != abbrChar) {
                    return false;
                }

                // Move to the next character in both word and abbreviation
                wordIndex++;
                abbrIndex++;
            }
        }

        // Both indices must reach the end for the abbreviation to be valid
        return wordIndex == word.length() && abbrIndex == abbr.length();
    }

    public static void main(String[] args) {
        // Example usage
        System.out.println(validWordAbbreviation("internationalization", "i12iz4n")); // Output: true
        System.out.println(validWordAbbreviation("apple", "a2e")); // Output: false
        System.out.println(validWordAbbreviation("apple", "a10")); // Output: false
        System.out.println(validWordAbbreviation("word", "w02d")); // Output: false
    }
}