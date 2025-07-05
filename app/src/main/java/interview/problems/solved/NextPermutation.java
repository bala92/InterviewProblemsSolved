package interview.problems.solved;

import java.util.Arrays;

/**
 * LeetCode 31: Next Permutation
 *
 * Problem Description:
 * Implement the function `nextPermutation` which rearranges numbers into the lexicographically next greater permutation of numbers.
 * If such an arrangement is not possible, it rearranges it as the lowest possible order (sorted in ascending order).
 *
 * The replacement must be in place and use only constant extra memory.
 *
 * Example 1:
 * Input: nums = [1, 2, 3]
 * Output: [1, 3, 2]
 *
 * Example 2:
 * Input: nums = [3, 2, 1]
 * Output: [1, 2, 3]
 *
 * Example 3:
 * Input: nums = [1, 1, 5]
 * Output: [1, 5, 1]
 *
 * Constraints:
 * - 1 <= nums.length <= 100
 * - 0 <= nums[i] <= 100
 *
 * Explanation:
 * The algorithm works in three main steps:
 * 1. **Find the first decreasing element from the right**:
 *    - Traverse the array from right to left to find the first index `i` such that `nums[i] < nums[i + 1]`.
 *    - If no such index exists, the array is in descending order, and we simply reverse it to get the smallest permutation.
 *
 * 2. **Find the next larger element to swap**:
 *    - If a decreasing element is found at index `i`, find the smallest element in the right subarray that is larger than `nums[i]`.
 *    - Swap these two elements to ensure the next permutation is larger.
 *
 * 3. **Reverse the right subarray**:
 *    - Reverse the subarray to the right of index `i` to get the smallest lexicographical order for the next permutation.
 *
 * Example Walkthrough:
 * Input: nums = [1, 2, 3]
 * - Step 1: Find the first decreasing element from the right. Here, `i = 1` (nums[1] = 2).
 * - Step 2: Find the next larger element to swap. Here, `j = 2` (nums[2] = 3). Swap nums[1] and nums[2].
 *   Array after swap: [1, 3, 2].
 * - Step 3: Reverse the subarray to the right of index `i`. Here, reverse nums[2:].
 *   Final result: [1, 3, 2].
 */
public class NextPermutation {

    /**
     * Rearranges numbers into the lexicographically next greater permutation of numbers.
     * If such an arrangement is not possible, it rearranges it as the lowest possible order (sorted in ascending order).
     * 
     * @param nums The array of numbers to find the next permutation for.
     */
    public static void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        // Step 1: Find the first decreasing element from the right
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        // Step 2: If a decreasing element is found, find the next larger element to swap
        if (i >= 0) {
            int j = nums.length - 1;
            while (nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j); // Swap the elements
        }

        // Step 3: Reverse the elements to the right of the swapped element
        reverse(nums, i + 1, nums.length - 1);
    }

    /**
     * Helper method to swap two elements in an array.
     * 
     * @param nums The array.
     * @param i The first index.
     * @param j The second index.
     */
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * Helper method to reverse a portion of an array.
     * 
     * @param nums The array.
     * @param start The starting index of the portion to reverse.
     * @param end The ending index of the portion to reverse.
     */
    private static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        // Example usage
        int[] nums = {1, 2, 3, 4, 5, 6};
        System.out.println("Original array: " + Arrays.toString(nums));

        nextPermutation(nums);
        System.out.println("Next permutation: " + Arrays.toString(nums));

        nextPermutation(nums);
        System.out.println("Next permutation: " + Arrays.toString(nums));

        nextPermutation(nums);
        System.out.println("Next permutation: " + Arrays.toString(nums));

        nextPermutation(nums);
        System.out.println("Next permutation: " + Arrays.toString(nums));

        nextPermutation(nums);
        System.out.println("Next permutation: " + Arrays.toString(nums));

        nextPermutation(nums);
        System.out.println("Next permutation: " + Arrays.toString(nums));
    }
}