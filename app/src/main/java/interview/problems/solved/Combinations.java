package interview.problems.solved;

/**
 * LeetCode 77: Combinations
 *
 * Problem Description:
 * ---------------------
 * Given two integers n and k, return all possible combinations of k numbers chosen from the range 1 to n.
 * You may return the answer in any order.
 *
 * Examples:
 * ---------
 * Example 1:
 * Input: n = 4, k = 2
 * Output: [
 *   [1,2],
 *   [1,3],
 *   [1,4],
 *   [2,3],
 *   [2,4],
 *   [3,4]
 * ]
 *
 * Example 2:
 * Input: n = 1, k = 1
 * Output: [[1]]
 *
 * Approach:
 * ---------
 * - Use backtracking to generate all possible combinations.
 * - Start from 1, and for each number, recursively add the next numbers to the current combination.
 * - When the current combination's size is k, add it to the result.
 * - Backtrack by removing the last added number and trying the next possibility.
 *
 * Complexity:
 * -----------
 * - Time Complexity: O(C(n, k) * k), where C(n, k) is the number of combinations.
 * - Space Complexity: O(C(n, k) * k), for storing all combinations.
 */

import java.util.*;

public class Combinations {

    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(1, n, k, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(int start, int n, int k, List<Integer> current, List<List<Integer>> result) {
        if (current.size() == k) {
            result.add(new ArrayList<>(current)); // Found a valid combination
            return;
        }
        for (int i = start; i <= n; i++) {
            current.add(i); // Choose the next number
            backtrack(i + 1, n, k, current, result); // Recurse
            current.remove(current.size() - 1); // Backtrack
        }
    }

    public static void main(String[] args) {
        int n = 4, k = 3;
        List<List<Integer>> combos = combine(n, k);
        System.out.println("All combinations:");
        for (List<Integer> combo : combos) {
            System.out.println(combo);
        }
    }
}