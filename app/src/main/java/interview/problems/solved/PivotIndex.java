package interview.problems.solved;

/**
 * LeetCode 724: Find Pivot Index
 *
 * Problem:
 * Given an array of integers nums, return the pivot index of this array.
 * The pivot index is the index where the sum of all the numbers strictly to the left of the index
 * is equal to the sum of all the numbers strictly to the right of the index.
 * If no such index exists, return -1. If there are multiple pivot indexes, return the left-most one.
 *
 * Example:
 * Input: nums = [1,7,3,6,5,6]
 * Output: 3
 * Explanation: The sum of the numbers to the left of index 3 (1 + 7 + 3 = 11)
 * is equal to the sum of numbers to the right of index 3 (5 + 6 = 11).
 *
 * Approach:
 * - Compute the total sum of the array.
 * - Iterate through the array, maintaining a running sum of elements to the left (leftSum).
 * - For each index, if leftSum == totalSum - leftSum - nums[i], return the index.
 * - If no such index is found, return -1.
 *
 * Time Complexity: O(n), where n is the length of nums (single pass after computing total sum).
 * Space Complexity: O(1), only constant extra space is used.
 */
public class PivotIndex {
    public int pivotIndex(int[] nums) {
        int totalSum = 0;
        for (int num : nums) totalSum += num;
        int leftSum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (leftSum == totalSum - leftSum - nums[i]) {
                return i;
            }
            leftSum += nums[i];
        }
        return -1;
    }

    /**
     * Alternative Approach: Two-pass prefix sum arrays
     *
     * - Compute prefix sums from the left and right for each index.
     * - For each index, if leftSum[i] == rightSum[i], return i.
     * - Time Complexity: O(n), Space Complexity: O(n)
     */
    public int pivotIndexWithPrefixArrays(int[] nums) {
        int n = nums.length;
        int[] leftSum = new int[n];
        int[] rightSum = new int[n];
        // Build left prefix sum
        for (int i = 1; i < n; i++) {
            leftSum[i] = leftSum[i - 1] + nums[i - 1];
        }
        // Build right prefix sum
        for (int i = n - 2; i >= 0; i--) {
            rightSum[i] = rightSum[i + 1] + nums[i + 1];
        }
        for (int i = 0; i < n; i++) {
            if (leftSum[i] == rightSum[i]) return i;
        }
        return -1;
    }

    // Example usage
    public static void main(String[] args) {
        PivotIndex solver = new PivotIndex();
        int[] nums1 = {1, 7, 3, 6, 5, 6};
        int[] nums2 = {1, 2, 3};
        int[] nums3 = {2, 1, -1};
        System.out.println("Pivot index for nums1: " + solver.pivotIndex(nums1)); // Output: 3
        System.out.println("Pivot index for nums2: " + solver.pivotIndex(nums2)); // Output: -1
        System.out.println("Pivot index for nums3: " + solver.pivotIndex(nums3)); // Output: 0
        System.out.println("Pivot index for nums1 (prefix arrays): " + solver.pivotIndexWithPrefixArrays(nums1)); // Output: 3
        System.out.println("Pivot index for nums2 (prefix arrays): " + solver.pivotIndexWithPrefixArrays(nums2)); // Output: -1
        System.out.println("Pivot index for nums3 (prefix arrays): " + solver.pivotIndexWithPrefixArrays(nums3)); // Output: 0
    }
}
