package interview.problems.solved;

/**
 * Problem Description:
 * ---------------------
 * Convert a non-negative integer to its English words representation.
 *
 * Approach:
 * ---------
 * 1. Divide the number into groups of three digits (thousands, millions, billions).
 * 2. Convert each group into words and append the appropriate scale (e.g., "Thousand", "Million", "Billion").
 * 3. Use recursion to handle each group of three digits.
 *
 * Complexity:
 * -----------
 * - Time Complexity: O(N), where N is the number of digits in the input number.
 * - Space Complexity: O(1), excluding the recursion stack.
 */
public class IntegerToEnglishWords {

    // Arrays for number-to-words mapping
    private static final String[] LESS_THAN_20 = {
        "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
        "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen",
        "Seventeen", "Eighteen", "Nineteen"
    };

    private static final String[] TENS = {
        "", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
    };

    private static final String[] THOUSANDS = {
        "", "Thousand", "Million", "Billion"
    };

    /**
     * Function to convert an integer to its English words representation.
     *
     * @param num The input integer.
     * @return The English words representation of the integer.
     */
    public static String numberToWords(int num) {
        if (num == 0) {
            return "Zero"; // Special case for 0
        }

        StringBuilder result = new StringBuilder();
        int index = 0;

        // Process each group of three digits
        while (num > 0) {
            if (num % 1000 != 0) {
                result.insert(0, helper(num % 1000) + THOUSANDS[index] + " ");
            }
            num /= 1000;
            index++;
        }

        return result.toString().trim();
    }

    /**
     * Helper function to convert a number less than 1000 to words.
     *
     * @param num The input number (less than 1000).
     * @return The English words representation of the number.
     */
    private static String helper(int num) {
        if (num == 0) {
            return "";
        } else if (num < 20) {
            return LESS_THAN_20[num] + " ";
        } else if (num < 100) {
            return TENS[num / 10] + " " + helper(num % 10);
        } else {
            return LESS_THAN_20[num / 100] + " Hundred " + helper(num % 100);
        }
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(numberToWords(123));          // Output: "One Hundred Twenty Three"
        System.out.println(numberToWords(12345));        // Output: "Twelve Thousand Three Hundred Forty Five"
        System.out.println(numberToWords(1234567));      // Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
        System.out.println(numberToWords(0));            // Output: "Zero"
        System.out.println(numberToWords(1000000));      // Output: "One Million"
        System.out.println(numberToWords(2147483647));   // Output: "Two Billion One Hundred Forty Seven Million Four Hundred Eighty Three Thousand Six Hundred Forty Seven"
    }
}