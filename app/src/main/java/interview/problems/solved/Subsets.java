package interview.problems.solved;

import java.util.*;

/**
 * LeetCode 78: Subsets, My Meta question Jun 30th 2025
 *
 * Problem:
 * Given an integer array nums of unique elements, return all possible subsets (the power set).
 * The solution set must not contain duplicate subsets.
 *
 * Example:
 * Input: nums = [1,2,3]
 * Output: [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
 *
 * Approach:
 * - Use backtracking to generate all possible subsets.
 * - At each step, decide whether to include the current element or not.
 * - Add the current subset to the result list at every recursion level.
 *
 * Time Complexity: O(n * 2^n), where n is the length of nums (2^n subsets, each can take up to O(n) to copy).
 * Space Complexity: O(n * 2^n), for storing all subsets.
 */
public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] nums, int start, List<Integer> current, List<List<Integer>> result) {
        result.add(new ArrayList<>(current));
        for (int i = start; i < nums.length; i++) {
            current.add(nums[i]);
            backtrack(nums, i + 1, current, result);
            current.remove(current.size() - 1); // Backtrack
        }
    }

    // Example usage
    public static void main(String[] args) {
        Subsets solver = new Subsets();
        int[] nums = {1, 2, 3};
        List<List<Integer>> allSubsets = solver.subsets(nums);
        System.out.println("All subsets:");
        for (List<Integer> subset : allSubsets) {
            System.out.println(subset);
        }
    }
}
