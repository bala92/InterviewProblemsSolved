package interview.problems.solved;

import java.util.*;

/**
 * LeetCode 113: Path Sum II
 *
 * Problem:
 * Given the root of a binary tree and an integer targetSum, return all root-to-leaf paths where each path's sum equals targetSum.
 *
 * Example:
 * Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * Output: [[5,4,11,2],[5,8,4,5]]
 *
 * Approach:
 * - Use DFS and backtracking to explore all root-to-leaf paths.
 * - Maintain a current path list and the remaining sum.
 * - When a leaf is reached and the sum matches, add a copy of the path to the result.
 * - Backtrack after exploring each node.
 *
 * Time Complexity: O(n), where n is the number of nodes (each node is visited once).
 * Space Complexity: O(h), where h is the height of the tree (recursion stack and path list).
 */
public class PathSum2 {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(root, targetSum, new ArrayList<>(), result);
        return result;
    }

    private void dfs(TreeNode node, int sum, List<Integer> path, List<List<Integer>> result) {
        if (node == null) return;
        path.add(node.val);
        if (node.left == null && node.right == null && sum == node.val) {
            result.add(new ArrayList<>(path));
        } else {
            dfs(node.left, sum - node.val, path, result);
            dfs(node.right, sum - node.val, path, result);
        }
        path.remove(path.size() - 1); // Backtrack
    }

    // Example usage
    public static void main(String[] args) {
        /*
         * Construct the following tree:
         *      5
         *     / \
         *    4   8
         *   /   / \
         * 11  13  4
         * / \     / \
         *7   2   5   1
         */
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.left = new TreeNode(5);
        root.right.right.right = new TreeNode(1);

        PathSum2 solver = new PathSum2();
        int targetSum = 22;
        List<List<Integer>> paths = solver.pathSum(root, targetSum);
        System.out.println("All root-to-leaf paths with sum 22:");
        for (List<Integer> path : paths) {
            System.out.println(path);
        }
    }
}
