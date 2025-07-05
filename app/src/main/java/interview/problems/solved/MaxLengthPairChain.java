package interview.problems.solved;

import java.util.Arrays;

/**
 * Problem Description:
 * ---------------------
 * Given an array of pairs, find the length of the longest chain of pairs such that
 * for each pair (a, b) in the chain, the next pair (c, d) satisfies b < c.
 *
 * Approach:
 * ---------
 * Use a greedy approach:
 * - Sort the pairs based on their second element.
 * - Iterate through the sorted pairs and greedily select pairs that can be added to the chain.
 *
 * Complexity:
 * -----------
 * - Time Complexity: O(n log n), where n is the number of pairs (due to sorting).
 * - Space Complexity: O(1), as no additional data structures are used.
 */
public class MaxLengthPairChain {

    /**
     * Function to find the maximum length of a pair chain.
     *
     * @param pairs The input array of pairs.
     * @return The maximum length of the pair chain.
     */
    public int findLongestChain(int[][] pairs) {
        // Sort pairs based on the second element
        Arrays.sort(pairs, (a, b) -> Integer.compare(a[1], b[1]));

        int maxLength = 0;
        int currentEnd = Integer.MIN_VALUE;

        // Iterate through the sorted pairs
        for (int[] pair : pairs) {
            if (pair[0] > currentEnd) {
                // Include the pair in the chain
                maxLength++;
                currentEnd = pair[1]; // Update the end of the current chain
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {
        MaxLengthPairChain solution = new MaxLengthPairChain();

        // Example 1
        int[][] pairs1 = {{1, 2}, {2, 3}, {3, 4}};
        System.out.println("Maximum Length of Pair Chain (Example 1): " + solution.findLongestChain(pairs1));
        // Output: 2

        // Example 2
        int[][] pairs2 = {{1, 2}, {7, 8}, {4, 5}};
        System.out.println("Maximum Length of Pair Chain (Example 2): " + solution.findLongestChain(pairs2));
        // Output: 3
    }
}