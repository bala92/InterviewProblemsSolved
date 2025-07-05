package interview.problems.solved;

/**
 * LeetCode 46/47: Permutations
 *
 * Problem Description:
 * ---------------------
 * Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.
 *
 * Examples:
 * ---------
 * Example 1:
 * Input: nums = [1,2,3]
 * Output: [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 *
 * Example 2:
 * Input: nums = [0,1]
 * Output: [
 *   [0,1],
 *   [1,0]
 * ]
 *
 * Approach:
 * ---------
 * - Use backtracking to generate all permutations.
 * - At each step, pick an unused number and add it to the current permutation.
 * - Mark the number as used, recurse, then backtrack (unmark and remove).
 * - When the current permutation's length equals nums.length, add it to the result.
 *
 * Complexity:
 * -----------
 * - Time Complexity: O(n * n!), where n is the length of nums.
 * - Space Complexity: O(n!), for storing all permutations.
 */

import java.util.*;

public class Permutations {

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        backtrack(nums, new ArrayList<>(), used, result);
        return result;
    }

    private static void backtrack(int[] nums, List<Integer> current, boolean[] used, List<List<Integer>> result) {
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current)); // Found a permutation
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue; // Skip used numbers
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue; // Skip duplicates: only use the first unused occurrence

            used[i] = true;
            current.add(nums[i]);
            backtrack(nums, current, used, result); // Recurse
            current.remove(current.size() - 1); // Backtrack
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 4 };
        List<List<Integer>> perms = permute(nums);
        System.out.println(String.format("All %s permutations:", perms.size()));
        for (List<Integer> perm : perms) {
            System.out.println(perm);
        }
    }
}