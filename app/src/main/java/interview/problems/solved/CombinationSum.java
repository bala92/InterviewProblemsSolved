package interview.problems.solved;

/*
LeetCode 39: Combination Sum

Problem Description:
Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.

The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the frequency of at least one of the chosen numbers is different.

Example 1:
Input: candidates = [2,3,6,7], target = 7
Output: [[2,2,3],[7]]

Example 2:
Input: candidates = [2,3,5], target = 8
Output: [[2,2,2,2],[2,3,3],[3,5]]

Constraints:
- 1 <= candidates.length <= 30
- 2 <= candidates[i] <= 40
- All elements of candidates are distinct.
- 1 <= target <= 40

Approach:
We use backtracking to explore all possible combinations. At each step, we can either include the current candidate (and stay at the same index, since we can reuse it) or move to the next candidate. We stop exploring a path if the sum exceeds the target. If the sum equals the target, we add the current combination to the result.

Time Complexity: O(2^t), where t is the target value (since each candidate can be used multiple times).
Space Complexity: O(t) for the recursion stack and combination storage.
*/

import java.util.*;

public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] candidates, int target, int start, List<Integer> current, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        if (target < 0) {
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            current.add(candidates[i]);
            backtrack(candidates, target - candidates[i], i, current, result); // not i + 1 because we can reuse same element
            current.remove(current.size() - 1);
        }
    }

    // Example usage and test
    public static void main(String[] args) {
        CombinationSum solver = new CombinationSum();
        int[] candidates1 = {2, 3, 6, 7};
        int target1 = 7;
        System.out.println("Combinations for [2,3,6,7], target=7: " + solver.combinationSum(candidates1, target1));

        int[] candidates2 = {2, 3, 5};
        int target2 = 8;
        System.out.println("Combinations for [2,3,5], target=8: " + solver.combinationSum(candidates2, target2));
    }
}
