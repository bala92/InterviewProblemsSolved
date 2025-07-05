package interview.problems.solved;

public class PowXN {

    /**
     * Calculates x raised to the power n (xⁿ).
     *
     * @param x The base.
     * @param n The exponent.
     * @return The result of xⁿ.
     */
    public static double myPow(double x, int n) {
        // Handle the case where n is negative
        long N = n; // Use long to handle edge cases like n = Integer.MIN_VALUE
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }

        double result = 1.0;
        double currentProduct = x;

        // Iteratively calculate the power
        while (N > 0) {
            if (N % 2 == 1) {
                result *= currentProduct; // Multiply the result by the current product if N is odd
            }
            currentProduct *= currentProduct; // Square the current product
            N /= 2; // Divide N by 2
        }

        return result;
    }

    public static void main(String[] args) {
        // Example 1
        double x1 = 2.00000;
        int n1 = 10;
        System.out.println("Result: " + myPow(x1, n1)); // Output: 1024.00000

        // Example 2
        double x2 = 2.10000;
        int n2 = 3;
        System.out.println("Result: " + myPow(x2, n2)); // Output: 9.26100

        // Example 3
        double x3 = 2.00000;
        int n3 = -2;
        System.out.println("Result: " + myPow(x3, n3)); // Output: 0.25000
    }
}

/**
 * Explanation:
 *
 * Approach:
 * - Use Exponentiation by Squaring to calculate xⁿ efficiently.
 * - If n is negative, convert the problem to 1 / pow(x, -n).
 * - Use an iterative approach:
 *   - If n is odd, multiply the result by the current product.
 *   - Square the current product and halve n.
 *
 * Example Walkthrough:
 * Input: x = 2.00000, n = 10
 * - Step 1: result = 1.0, currentProduct = 2.0, N = 10
 * - Step 2: N is even → currentProduct = 4.0, N = 5
 * - Step 3: N is odd → result = 4.0, currentProduct = 16.0, N = 2
 * - Step 4: N is even → currentProduct = 256.0, N = 1
 * - Step 5: N is odd → result = 1024.0, currentProduct = 65536.0, N = 0
 * Output: 1024.0
 *
 * Complexity:
 * - Time Complexity: O(log n), where n is the exponent.
 * - Space Complexity: O(1), as no additional space is used.
 */