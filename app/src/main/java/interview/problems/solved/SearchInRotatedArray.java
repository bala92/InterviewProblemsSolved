package interview.problems.solved;

/**
 * LeetCode 33: Search in Rotated Sorted Array
 *
 * Problem Description:
 * ---------------------
 * You are given an integer array `nums` sorted in ascending order (with distinct values), which is rotated at some pivot unknown to you beforehand.
 * Given the array `nums` and an integer `target`, return the index of `target` if it is in `nums`, or `-1` if it is not.
 *
 * Examples:
 * ---------
 * Example 1:
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 *
 * Example 2:
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 *
 * Example 3:
 * Input: nums = [1], target = 0
 * Output: -1
 *
 * Approach:
 * ---------
 * - Use a modified binary search to handle the rotated sorted array.
 * - The array is divided into two sorted subarrays due to the rotation.
 * - At each step, determine which part of the array (left or right) is sorted.
 * - Narrow down the search range based on the target's position relative to the middle element.
 *
 * Complexity:
 * -----------
 * - Time Complexity: O(log n), where n is the length of the array.
 * - Space Complexity: O(1), as no additional space is used.
 */

public class SearchInRotatedArray {

    /**
     * Searches for the target in a rotated sorted array.
     *
     * @param nums   The rotated sorted array.
     * @param target The target value to search for.
     * @return The index of the target if found, otherwise -1.
     */
    public static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1; // Handle edge case of an empty array
        }

        int left = 0, right = nums.length - 1;

        // Perform binary search
        while (left <= right) {
            int mid = left + (right - left) / 2; // Calculate the middle index

            // Check if the middle element is the target
            if (nums[mid] == target) {
                return mid; // Target found
            }

            // Determine which part of the array is sorted
            if (nums[left] <= nums[mid]) {
                // Left portion is sorted
                if (nums[left] <= target && target < nums[mid]) {
                    // Target lies in the left sorted portion
                    right = mid - 1;
                } else {
                    // Target lies in the right portion
                    left = mid + 1;
                }
            } else {
                // Right portion is sorted
                if (nums[mid] < target && target <= nums[right]) {
                    // Target lies in the right sorted portion
                    left = mid + 1;
                } else {
                    // Target lies in the left portion
                    right = mid - 1;
                }
            }
        }

        return -1; // Target not found
    }

    public static void main(String[] args) {
        // Example 1
        int[] nums1 = {4, 5, 6, 7, 0, 1, 2};
        int target1 = 0;
        System.out.println("Index of target (Example 1): " + search(nums1, target1)); // Output: 4

        // Example 2
        int[] nums2 = {4, 5, 6, 7, 0, 1, 2};
        int target2 = 3;
        System.out.println("Index of target (Example 2): " + search(nums2, target2)); // Output: -1

        // Example 3
        int[] nums3 = {1};
        int target3 = 0;
        System.out.println("Index of target (Example 3): " + search(nums3, target3)); // Output: -1
    }
}