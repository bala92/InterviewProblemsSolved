package interview.problems.solved;

/**
 * LeetCode 153: Find Minimum in Rotated Sorted Array
 *
 * Problem Description:
 * ---------------------
 * Suppose an array of length `n` sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * (e.g., [4,5,6,7,0,1,2] is rotated at pivot 4).
 * You are tasked with finding the minimum element in the array.
 * You must write an algorithm that runs in O(log n) time.
 *
 * Examples:
 * ---------
 * Example 1:
 * Input: nums = [3,4,5,1,2]
 * Output: 1
 *
 * Example 2:
 * Input: nums = [4,5,6,7,0,1,2]
 * Output: 0
 *
 * Example 3:
 * Input: nums = [11,13,15,17]
 * Output: 11
 *
 * Approach:
 * ---------
 * - Use binary search to find the minimum element in the rotated sorted array.
 * - Compare the middle element with the rightmost element to determine which part of the array to search:
 *   1. If nums[mid] > nums[right], the minimum lies in the right half (excluding mid).
 *   2. Otherwise, the minimum lies in the left half (including mid).
 * - Continue narrowing the search range until left == right, which will point to the minimum element.
 *
 * Complexity:
 * -----------
 * - Time Complexity: O(log n), where n is the length of the array.
 * - Space Complexity: O(1), as no additional space is used.
 */

public class FindMinimumInRotatedArray {

    /**
     * Finds the minimum element in a rotated sorted array.
     *
     * @param nums The rotated sorted array.
     * @return The minimum element in the array.
     */
    public static int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Input array cannot be null or empty");
        }

        int left = 0, right = nums.length - 1;

        // Perform binary search to find the minimum element
        while (left < right) {
            int mid = left + (right - left) / 2; // Calculate the middle index

            // Compare the middle element with the rightmost element
            if (nums[mid] > nums[right]) {
                // If nums[mid] > nums[right], the minimum must be in the right half
                left = mid + 1;
            } else {
                // Otherwise, the minimum is in the left half (including mid)
                right = mid;
            }
        }

        // At the end of the loop, left == right, pointing to the minimum element
        return nums[left];
    }

    public static void main(String[] args) {
        // Example 1
        int[] nums1 = {3, 4, 5, 1, 2};
        System.out.println("Minimum element (Example 1): " + findMin(nums1)); // Output: 1

        // Example 2
        int[] nums2 = {4, 5, 6, 7, 0, 1, 2};
        System.out.println("Minimum element (Example 2): " + findMin(nums2)); // Output: 0

        // Example 3
        int[] nums3 = {11, 13, 15, 17};
        System.out.println("Minimum element (Example 3): " + findMin(nums3)); // Output: 11
    }
}