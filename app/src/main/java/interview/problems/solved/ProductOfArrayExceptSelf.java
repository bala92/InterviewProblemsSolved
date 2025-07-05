package interview.problems.solved;

/**
 * LeetCode 238: Product of Array Except Self
 *
 * Problem Description:
 * Given an integer array nums, return an array answer such that answer[i] is equal to the product
 * of all the elements of nums except nums[i].
 *
 * The solution must solve the problem without using division and in O(n) time complexity.
 *
 * Example 1:
 * Input: nums = [1, 2, 3, 4]
 * Output: [24, 12, 8, 6]
 *
 * Example 2:
 * Input: nums = [-1, 1, 0, -3, 3]
 * Output: [0, 0, 9, 0, 0]
 *
 * Constraints:
 * - 2 <= nums.length <= 10^5
 * - -30 <= nums[i] <= 30
 * - The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 *
 * Explanation:
 * The solution uses a two-pass approach:
 * 1. Compute the cumulative product of all elements to the left of each index.
 * 2. Compute the cumulative product of all elements to the right of each index while updating the result array.
 * This avoids the use of division and ensures O(n) time complexity.
 */
public class ProductOfArrayExceptSelf {

    /**
     * Computes the product of array except self without using division.
     *
     * @param nums The input array.
     * @return The product array.
     */
    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        // Step 1: Compute left products
        result[0] = 1; // No elements to the left of the first element
        for (int i = 1; i < n; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }

        // Step 2: Compute right products and update the result array
        int rightProduct = 1; // No elements to the right of the last element
        for (int i = n - 1; i >= 0; i--) {
            result[i] = result[i] * rightProduct;
            rightProduct *= nums[i];
        }

        return result;
    }

    public static void main(String[] args) {
        // Example 1
        int[] nums1 = {10, 2, 3, 4};
        int[] result1 = productExceptSelf(nums1);
        System.out.println("Product Except Self (Example 1):");
        for (int num : result1) {
            System.out.print(num + " ");
        }
        System.out.println();

        // Example 2
        int[] nums2 = {-1, 1, 0, -3, 3};
        int[] result2 = productExceptSelf(nums2);
        System.out.println("Product Except Self (Example 2):");
        for (int num : result2) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}