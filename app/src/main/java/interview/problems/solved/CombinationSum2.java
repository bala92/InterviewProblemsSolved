package interview.problems.solved;

import java.util.*;

/**
 * LeetCode 40: Combination Sum II
 *
 * Problem:
 * Given a collection of candidate numbers (candidates) and a target number (target),
 * find all unique combinations in candidates where the candidate numbers sum to target.
 * Each number in candidates may only be used once in the combination.
 *
 * Note:
 * - All numbers (including target) will be positive integers.
 * - The solution set must not contain duplicate combinations.
 *
 * Example:
 * Input: candidates = [10,1,2,7,6,1,5], target = 8
 * Output: [
 *   [1,1,6],
 *   [1,2,5],
 *   [1,7],
 *   [2,6]
 * ]
 *
 * Approach:
 * - Sort the candidates array to handle duplicates easily.
 * - Use backtracking to explore all possible combinations.
 * - Skip duplicates by checking if the current candidate is the same as the previous one at the same recursion depth.
 * - Only use each candidate once per combination (move to i+1 in recursion).
 *
 * Time Complexity: O(2^n) in the worst case (n = number of candidates), but pruning and duplicate skipping make it faster in practice.
 * Space Complexity: O(n) for recursion stack and combination storage.
 */
public class CombinationSum2 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<>();
        Arrays.sort(candidates); // Sort to handle duplicates
        backtrack(candidates, target, 0, new ArrayList<>(), results);
        return results;
    }

    private void backtrack(int[] candidates, int remain, int start, List<Integer> comb, List<List<Integer>> results) {
        if (remain == 0) {
            results.add(new ArrayList<>(comb));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            // Skip duplicates
            if (i > start && candidates[i] == candidates[i - 1]) continue;
            if (candidates[i] > remain) break; // No need to continue if the number is too large
            comb.add(candidates[i]);
            backtrack(candidates, remain - candidates[i], i + 1, comb, results); // i+1: each number used once
            comb.remove(comb.size() - 1);
        }
    }

    // Example usage
    public static void main(String[] args) {
        CombinationSum2 solver = new CombinationSum2();
        int[] candidates = {10,1,2,7,6,1,5};
        int target = 8;
        List<List<Integer>> result = solver.combinationSum2(candidates, target);
        System.out.println("Unique combinations that sum to " + target + ":");
        for (List<Integer> comb : result) {
            System.out.println(comb);
        }
    }
}
