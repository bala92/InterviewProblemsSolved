package interview.problems.solved;

/**
 * Problem Description:
 * ---------------------
 * Given a positive integer n, find the minimum number of steps required to reduce it to 1.
 * You can perform the following operations:
 * 1. If n is divisible by 3, divide it by 3.
 * 2. If n is divisible by 2, divide it by 2.
 * 3. Subtract 1 from n.
 *
 * Approach:
 * ---------
 * 1. Use dynamic programming to calculate the minimum steps for each number from 1 to n.
 * 2. Use a DP array where dp[i] represents the minimum steps to reduce i to 1.
 *
 * Complexity:
 * -----------
 * - Time Complexity: O(n), where n is the input number.
 * - Space Complexity: O(n), for the DP array.
 */
public class MinStepsToOne {

    /**
     * Function to calculate the minimum steps to reduce n to 1.
     *
     * @param n The input number.
     * @return The minimum number of steps to reduce n to 1.
     */
    public static int minStepsToOne(int n) {
        // DP array to store the minimum steps for each number
        int[] dp = new int[n + 1];

        // Base case: 1 requires 0 steps to reduce to 1
        dp[1] = 0;

        // Fill the DP array for numbers from 2 to n
        for (int i = 2; i <= n; i++) {
            // Subtract 1
            dp[i] = dp[i - 1] + 1;

            // Divide by 2 (if divisible)
            if (i % 2 == 0) {
                dp[i] = Math.min(dp[i], dp[i / 2] + 1);
            }

            // Divide by 3 (if divisible)
            if (i % 3 == 0) {
                dp[i] = Math.min(dp[i], dp[i / 3] + 1);
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        // Example 1
        int n1 = 10;
        System.out.println("Minimum steps to reduce " + n1 + " to 1: " + minStepsToOne(n1));
        // Output: 3 (10 -> 9 -> 3 -> 1)

        // Example 2
        int n2 = 15;
        System.out.println("Minimum steps to reduce " + n2 + " to 1: " + minStepsToOne(n2));
        // Output: 4 (15 -> 14 -> 7 -> 6 -> 1)

        // Example 3
        int n3 = 1;
        System.out.println("Minimum steps to reduce " + n3 + " to 1: " + minStepsToOne(n3));
        // Output: 0
    }
}