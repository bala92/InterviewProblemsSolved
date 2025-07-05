package interview.problems.solved;

import java.util.HashMap;

/**
 * LeetCode 974: Subarray Sums Divisible by K
 *
 * Problem:
 * Given an integer array nums and an integer k, return the number of non-empty subarrays that have a sum divisible by k.
 *
 * Example:
 * Input: nums = [4,5,0,-2,-3,1], k = 5
 * Output: 7
 * Explanation: There are 7 subarrays with a sum divisible by 5: [4,5,0,-2,-3,1], [5], [5,0], [5,0,-2,-3], [0], [0,-2,-3], [-2,-3,1]
 *
 * Approach:
 * - Use a HashMap to store the count of each prefix sum remainder modulo k.
 * - For each element, compute the running prefix sum and its remainder (modulo k).
 * - If the same remainder has been seen before, it means the subarray between those indices has a sum divisible by k.
 * - Add the count of the current remainder to the result and update the map.
 * - Handle negative remainders by normalizing them to the range [0, k-1].
 *
 * Time Complexity: O(n), where n is the length of nums (single pass).
 * Space Complexity: O(k), for the HashMap storing up to k remainders.
 */
public class SubarraySumDivisibleByK {
    public int subarraysDivByK(int[] nums, int k) {
        HashMap<Integer, Integer> modCount = new HashMap<>();
        modCount.put(0, 1); // Base case: sum 0 is divisible by k
        int prefixSum = 0, result = 0;
        for (int num : nums) {
            prefixSum += num;
            int mod = ((prefixSum % k) + k) % k; // Normalize negative mods
            result += modCount.getOrDefault(mod, 0);
            modCount.put(mod, modCount.getOrDefault(mod, 0) + 1);
        }
        return result;
    }

    // Example usage
    public static void main(String[] args) {
        SubarraySumDivisibleByK solver = new SubarraySumDivisibleByK();
        int[] nums = {4, 5, 0, -2, -3, 1};
        int k = 5;
        System.out.println("Number of subarrays divisible by k: " + solver.subarraysDivByK(nums, k)); // Output: 7
    }
}
