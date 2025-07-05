package interview.problems.solved;

/**
 * LeetCode 29: Divide Two Integers
 *
 * Problem Description:
 * ---------------------
 * Given two integers dividend and divisor, divide two integers without using multiplication, division, and mod operator.
 * Return the quotient after dividing dividend by divisor.
 * The integer division should truncate toward zero, which means losing its fractional part.
 *
 * Note:
 * - Assume we are dealing with an environment that could only store integers within the 32-bit signed integer range: [−2^31,  2^31 − 1].
 * - For this problem, if the quotient is strictly greater than 2^31 − 1, return 2^31 − 1.
 * - If the quotient is strictly less than −2^31, return −2^31.
 *
 * Example 1:
 * Input: dividend = 10, divisor = 3
 * Output: 3
 *
 * Example 2:
 * Input: dividend = 7, divisor = -3
 * Output: -2
 *
 * Constraints:
 * - -2^31 <= dividend, divisor <= 2^31 - 1
 * - divisor != 0
 *
 * Approach (Without Bit Manipulation):
 * ------------------------------------
 * - Use repeated subtraction to simulate division.
 * - Convert both dividend and divisor to positive values, keep track of the sign.
 * - Subtract divisor from dividend until dividend < divisor, incrementing the quotient each time.
 * - Apply the correct sign to the result.
 * - Handle overflow cases.
 *
 * Complexity:
 * -----------
 * - Time Complexity: O(N), where N is the absolute value of the quotient (not efficient for large numbers).
 * - Space Complexity: O(1).
 */
public class DivideTwoIntegers {

    public int divide(int dividend, int divisor) {
        // Handle overflow
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        // Determine the sign of the result
        boolean negative = (dividend < 0) ^ (divisor < 0);

        // Convert both numbers to long and take absolute value to avoid overflow
        long a = Math.abs((long) dividend);
        long b = Math.abs((long) divisor);

        int result = 0;

        // Repeatedly subtract divisor from dividend
        while (a >= b) {
            a -= b;
            result++;
        }

        return negative ? -result : result;
    }

    public int divideInLogNTime(int dividend, int divisor) {
    if (dividend == Integer.MIN_VALUE && divisor == -1) {
        return Integer.MAX_VALUE;
    }
    boolean negative = (dividend < 0) ^ (divisor < 0);
    long a = Math.abs((long) dividend);
    long b = Math.abs((long) divisor);
    int result = 0;

    while (a >= b) {
        long temp = b;
        int multiple = 1;
        // Double temp and multiple until temp << 1 would be too big
        while (a >= (temp + temp)) {
            temp += temp;
            multiple += multiple;
        }
        a -= temp;
        result += multiple;
    }
    return negative ? -result : result;
}

    public static void main(String[] args) {
        DivideTwoIntegers solution = new DivideTwoIntegers();
        System.out.println("10 / 3 = " + solution.divide(10, 3));      // Output: 3
        System.out.println("7 / -3 = " + solution.divide(7, -3));      // Output: -2
        System.out.println("-2147483648 / -1 = " + solution.divide(-2147483648, -1)); // Output: 2147483647
        System.out.println("-15 / 2 = " + solution.divide(-15, 2));    // Output: -7
        System.out.println("-15 / 2 = " + solution.divideInLogNTime(-15, 2));    // Output: -7

    }
}