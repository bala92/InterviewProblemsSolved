package interview.problems.solved;

import java.util.HashMap;

/**
 * LeetCode 560: Subarray Sum Equals K
 *
 * Problem:
 * Given an array of integers nums and an integer k, return the total number of continuous subarrays whose sum equals to k.
 *
 * Example:
 * Input: nums = [1,1,1], k = 2
 * Output: 2
 * Explanation: The subarrays [1,1] (first and second elements) and [1,1] (second and third elements) both sum to 2.
 *
 * Approach:
 * - Use a HashMap to store the cumulative sum up to each index and the number of times the sum has occurred.
 * - For each element, calculate the current cumulative sum.
 * - If (currentSum - k) exists in the map, it means there is a subarray ending at the current index with sum k.
 * - Add the count of (currentSum - k) to the result.
 * - Update the map with the current cumulative sum.
 *
 * Time Complexity: O(n), where n is the length of nums (single pass).
 * Space Complexity: O(n), for the HashMap.
 */
public class SubarraySumEqualsK {
    public int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> sumCount = new HashMap<>();
        sumCount.put(0, 1); // Base case: sum 0 occurs once
        int currentSum = 0, result = 0;
        for (int num : nums) {
            currentSum += num;
            if (sumCount.containsKey(currentSum - k)) {
                result += sumCount.get(currentSum - k);
            }
            sumCount.put(currentSum, sumCount.getOrDefault(currentSum, 0) + 1);
        }
        return result;
    }

    // Example usage
    public static void main(String[] args) {
        SubarraySumEqualsK solver = new SubarraySumEqualsK();
        int[] nums1 = {1, 1, 1};
        int k1 = 2;
        int[] nums2 = {1, 2, 3, 5, 5, 2};
        int k2 = 8;
        System.out.println("Number of subarrays (nums1, k1): " + solver.subarraySum(nums1, k1)); // Output: 2
        System.out.println("Number of subarrays (nums2, k2): " + solver.subarraySum(nums2, k2)); // Output: 2
    }
}
