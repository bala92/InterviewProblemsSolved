package interview.problems.solved;

/**
 * LeetCode 209: Minimum Size Subarray Sum
 *
 * Problem:
 * Given an array of positive integers nums and a positive integer target, return the minimal length of a
 * contiguous subarray of which the sum is greater than or equal to target. If there is no such subarray, return 0 instead.
 *
 * Example:
 * Input: target = 7, nums = [2,3,1,2,4,3]
 * Output: 2
 * Explanation: The subarray [4,3] has the minimal length under the problem constraint.
 *
 * Approach:
 * - Use the sliding window technique with two pointers (start and end).
 * - Expand the window by moving the end pointer and add nums[end] to the sum.
 * - When the sum is greater than or equal to target, try to shrink the window from the start to find the minimal length.
 * - Continue until the end of the array.
 *
 * Time Complexity: O(n), where n is the length of nums. Each element is visited at most twice.
 * Space Complexity: O(1), only constant extra space is used.
 */
public class MinSizeSubarraySum {
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        int minLen = Integer.MAX_VALUE;
        int sum = 0, left = 0;
        for (int right = 0; right < n; right++) {
            sum += nums[right];
            while (sum >= target) {
                minLen = Math.min(minLen, right - left + 1);
                sum -= nums[left++];
            }
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    // Example usage
    public static void main(String[] args) {
        MinSizeSubarraySum solver = new MinSizeSubarraySum();
        int[] nums = {2, 3, 1, 2, 4, 3};
        int target = 7;
        int result = solver.minSubArrayLen(target, nums);
        System.out.println("Minimal length of a subarray: " + result); // Output: 2
    }
}
