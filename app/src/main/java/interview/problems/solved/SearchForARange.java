package interview.problems.solved;

/**
 * LeetCode 34: Find First and Last Position of Element in Sorted Array
 *
 * Problem Description:
 * ---------------------
 * Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.
 * If target is not found in the array, return [-1, -1].
 * You must write an algorithm with O(log n) runtime complexity.
 *
 * Examples:
 * ---------
 * Example 1:
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 *
 * Example 2:
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 *
 * Example 3:
 * Input: nums = [], target = 0
 * Output: [-1,-1]
 *
 * Approach:
 * ---------
 * - Use binary search to find the first occurrence (left boundary) of the target.
 * - Use binary search to find the last occurrence (right boundary) of the target.
 * - If the target is not found, return [-1, -1].
 *
 * Complexity:
 * -----------
 * - Time Complexity: O(log n), where n is the length of nums.
 * - Space Complexity: O(1).
 */

public class SearchForARange {

    public static int[] searchRange(int[] nums, int target) {
        int[] result = {-1, -1};
        if (nums == null || nums.length == 0) return result;

        // Find the first occurrence (left boundary)
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        // Check if target is present
        if (left >= nums.length || nums[left] != target) return result;
        result[0] = left;

        // Find the last occurrence (right boundary)
        right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        result[1] = right;

        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = {5,7,7,8,8,8,10};
        int target1 = 8;
        int[] res1 = searchRange(nums1, target1);
        System.out.println("Range for 8: [" + res1[0] + ", " + res1[1] + "]"); // Output: [3,5]

        int[] nums2 = {5,7,7,8,8,10};
        int target2 = 6;
        int[] res2 = searchRange(nums2, target2);
        System.out.println("Range for 6: [" + res2[0] + ", " + res2[1] + "]"); // Output: [-1,-1]
    }
}