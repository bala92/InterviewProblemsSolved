package interview.problems.solved;

import java.util.*;

/**
 * LeetCode 90: Subsets II
 *
 * Problem:
 * Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).
 * The solution set must not contain duplicate subsets.
 *
 * Example:
 * Input: nums = [1,2,2]
 * Output: [
 *   [2],
 *   [1],
 *   [1,2,2],
 *   [2,2],
 *   [1,2],
 *   []
 * ]
 *
 * Approach:
 * - Sort the input array to bring duplicates together.
 * - Use backtracking to generate all possible subsets.
 * - At each recursion level, skip duplicate elements to avoid duplicate subsets.
 *
 * Time Complexity: O(2^n), where n is the length of nums. Each element can be either included or not.
 * Space Complexity: O(2^n) for storing all subsets, and O(n) for the recursion stack.
 */
public class Subsets2 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums); // Sort to handle duplicates
        backtrack(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] nums, int start, List<Integer> tempList, List<List<Integer>> result) {
        result.add(new ArrayList<>(tempList));
        for (int i = start; i < nums.length; i++) {
            // Skip duplicates
            if (i > start && nums[i] == nums[i - 1]) continue;
            tempList.add(nums[i]);
            backtrack(nums, i + 1, tempList, result);
            tempList.remove(tempList.size() - 1);
        }
    }

    // Example usage
    public static void main(String[] args) {
        Subsets2 solver = new Subsets2();
        int[] nums = {1, 2, 2};
        List<List<Integer>> result = solver.subsetsWithDup(nums);
        System.out.println("All unique subsets:");
        for (List<Integer> subset : result) {
            System.out.println(subset);
        }
    }
}
