package interview.problems.solved;

import java.util.Random;

/**
 * LeetCode 528: Random Pick with Weight
 *
 * Problem Description:
 * ---------------------
 * You are given an array of positive integers `w` where `w[i]` describes the weight of index `i`.
 * Write a function `pickIndex()` which randomly picks an index in proportion to its weight.
 *
 * Example:
 * --------
 * Input:
 * weights = [1, 3, 4, 10]
 * Output:
 * The function should return indices 0, 1, 2, or 3 randomly, but the probability of picking an index
 * is proportional to its weight:
 * - Index 0: Probability = 1 / (1 + 3 + 4 + 10) = 1 / 18
 * - Index 1: Probability = 3 / 18
 * - Index 2: Probability = 4 / 18
 * - Index 3: Probability = 10 / 18
 *
 * Approach:
 * ---------
 * - Use a prefix sum array to represent cumulative weights.
 * - Generate a random number between 1 and the total sum of weights.
 * - Use binary search to find the smallest index in the prefix sum array that is greater than or equal to the random number.
 *
 * Complexity:
 * -----------
 * - Constructor: O(n), where n is the length of the weights array (to build the prefix sum array).
 * - pickIndex(): O(log n), where n is the length of the weights array (binary search).
 * - Space Complexity: O(n), for storing the prefix sum array.
 */

public class RandomPickWithWeight {

    private int[] prefixSums; // Prefix sum array to store cumulative weights
    private int totalSum; // Total sum of all weights
    private Random random; // Random number generator

    /**
     * Constructor to initialize the object with the given weights.
     * @param w Array of weights where w[i] represents the weight of index i.
     */
    public RandomPickWithWeight(int[] w) {
        this.prefixSums = new int[w.length];
        this.totalSum = 0;
        this.random = new Random();

        // Build the prefix sum array
        for (int i = 0; i < w.length; i++) {
            totalSum += w[i]; // Add the current weight to the total sum
            prefixSums[i] = totalSum; // Store the cumulative sum in the prefix sum array
        }
    }

    /**
     * Picks an index randomly in proportion to its weight.
     * @return The index picked based on the weights.
     */
    public int pickIndex() {
        // Generate a random number between 1 and totalSum (inclusive)
        int target = random.nextInt(totalSum) + 1;

        // Binary search to find the index
        int left = 0, right = prefixSums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2; // Calculate the middle index
            if (prefixSums[mid] < target) {
                // If the target is greater than the prefix sum at mid, move to the right half
                left = mid + 1;
            } else {
                // Otherwise, move to the left half
                right = mid;
            }
        }

        // Return the index where the target fits in the prefix sum array
        return left;
    }

    public static void main(String[] args) {
        // Example usage
        int[] weights = {1, 3, 4, 10};
        RandomPickWithWeight solution = new RandomPickWithWeight(weights);

        // Simulate multiple picks to observe the distribution
        System.out.println("Picked index: " + solution.pickIndex());
        System.out.println("Picked index: " + solution.pickIndex());
        System.out.println("Picked index: " + solution.pickIndex());
    }
}