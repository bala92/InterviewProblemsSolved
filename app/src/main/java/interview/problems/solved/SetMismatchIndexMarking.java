package interview.problems.solved;

import java.util.Arrays;

/**
 * Problem Description:
 * ---------------------
 * Given an integer array nums of length n where the numbers range from 1 to n,
 * one number is duplicated, and one number is missing. Return the duplicate
 * and missing numbers as a pair.
 *
 * Approach:
 * ---------
 * Use the index marking approach:
 * - Iterate through the array and mark the index corresponding to each number as visited by negating the value.
 * - Identify the duplicate number when an index is visited twice (already negative).
 * - Identify the missing number as the index that remains positive after marking.
 *
 * Complexity:
 * -----------
 * - Time Complexity: O(n)
 * - Space Complexity: O(1)
 */
public class SetMismatchIndexMarking {

    /**
     * Function to find the duplicate and missing numbers in the array.
     *
     * @param nums The input array.
     * @return An array containing the duplicate and missing numbers.
     */
    public int[] findErrorNums(int[] nums) {
        int duplicate = -1;
        int missing = -1;

        // Step 1: Mark visited indices
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1; // Get the index corresponding to the current number
            if (nums[index] < 0) {
                duplicate = Math.abs(nums[i]); // If already negative, it's the duplicate
            } else {
                nums[index] = -nums[index]; // Mark the index as visited by negating the value
            }
        }

        // Step 2: Identify the missing number
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                missing = i + 1; // If the value is positive, the index + 1 is the missing number
            }
        }

        return new int[]{duplicate, missing};
    }

    public static void main(String[] args) {
        SetMismatchIndexMarking solution = new SetMismatchIndexMarking();

        // Example 1
        int[] nums1 = {1, 2, 2, 4};
        System.out.println("Result (Example 1): " + Arrays.toString(solution.findErrorNums(nums1)));
        // Output: [2, 3]

        // Example 2
        int[] nums2 = {1, 1};
        System.out.println("Result (Example 2): " + Arrays.toString(solution.findErrorNums(nums2)));
        // Output: [1, 2]

        // Example 3
        int[] nums3 = {3, 2, 3, 4, 6, 5};
        System.out.println("Result (Example 3): " + Arrays.toString(solution.findErrorNums(nums3)));
        // Output: [3, 1]

        // Example 4
        int[] nums4 = {2, 2};
        System.out.println("Result (Example 4): " + Arrays.toString(solution.findErrorNums(nums4)));
        // Output: [2, 1]
    }
}