package interview.problems.solved;

public class MonotoneIncreasingDigits {

    /**
     * Function to find the largest number less than or equal to n with monotone increasing digits.
     *
     * @param n The input number.
     * @return The largest number with monotone increasing digits.
     */
    public int monotoneIncreasingDigits(int n) {
        char[] digits = String.valueOf(n).toCharArray();
        int marker = digits.length;

        // Traverse the digits from right to left to find violations
        for (int i = digits.length - 1; i > 0; i--) {
            if (digits[i] < digits[i - 1]) {
                digits[i - 1]--; // Decrement the previous digit
                marker = i;     // Mark the position where digits need to be set to '9'
            }
        }

        // Set all digits after the marker to '9'
        for (int i = marker; i < digits.length; i++) {
            digits[i] = '9';
        }

        return Integer.parseInt(new String(digits));
    }

    public static void main(String[] args) {
        MonotoneIncreasingDigits solution = new MonotoneIncreasingDigits();

        // Example 1
        int n1 = 1234;
        System.out.println("Largest monotone increasing digits (Example 1): " + solution.monotoneIncreasingDigits(n1));
        // Output: 1234

        // Example 2
        int n2 = 332;
        System.out.println("Largest monotone increasing digits (Example 2): " + solution.monotoneIncreasingDigits(n2));
        // Output: 299

        // Example 3
        int n3 = 10;
        System.out.println("Largest monotone increasing digits (Example 3): " + solution.monotoneIncreasingDigits(n3));
        // Output: 9
    }
}