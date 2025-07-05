package interview.problems.solved;

/**
 * LeetCode 152: Maximum Product Subarray
 *
 * Problem Description:
 * ---------------------
 * Given an integer array nums, find the contiguous subarray within an array (containing at least one number)
 * which has the largest product, and return the product.
 *
 * Example 1:
 * Input: nums = [2,3,-2,4]
 * Output: 6
 * Explanation: [2,3] has the largest product = 6.
 *
 * Example 2:
 * Input: nums = [-2,0,-1]
 * Output: 0
 * Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 *
 * Approach:
 * ---------
 * - Use dynamic programming to keep track of both the maximum and minimum product ending at the current position,
 *   because a negative number could turn a large minimum product into a maximum when multiplied.
 * - For each element, calculate the maximum and minimum products up to that point.
 * - The answer is the maximum of all these products.
 *
 * Complexity:
 * -----------
 * - Time Complexity: O(n), where n is the length of the array.
 * - Space Complexity: O(1), since only a few variables are used.
 */
public class MaximumProductSubarray {

    /**
     * Function to find the maximum product subarray.
     *
     * @param nums The input array.
     * @return The maximum product of any contiguous subarray.
     */
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int maxSoFar = nums[0];
        int minSoFar = nums[0];
        int result = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int tempMax = Math.max(nums[i], Math.max(maxSoFar * nums[i], minSoFar * nums[i]));
            minSoFar = Math.min(nums[i], Math.min(maxSoFar * nums[i], minSoFar * nums[i])); //tempMax is used because maxSoFar could change while calculating minSoFar
            maxSoFar = tempMax;
            result = Math.max(result, maxSoFar);
        }

        return result;
    }

    public static void main(String[] args) {
        MaximumProductSubarray solution = new MaximumProductSubarray();

        int[] nums1 = {2, 3, -2, 4};
        System.out.println("Maximum Product Subarray (Example 1): " + solution.maxProduct(nums1)); // Output: 6

        int[] nums2 = {-2, 0, -1};
        System.out.println("Maximum Product Subarray (Example 2): " + solution.maxProduct(nums2)); // Output: 0

        int[] nums3 = {-2, 3, -4};
        System.out.println("Maximum Product Subarray (Example 3): " + solution.maxProduct(nums3)); // Output: 24
    }
}