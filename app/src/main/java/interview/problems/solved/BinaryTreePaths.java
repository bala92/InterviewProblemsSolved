package interview.problems.solved;

import java.util.*;

/**
 * LeetCode 257: Binary Tree Paths
 *
 * Problem:
 * Given the root of a binary tree, return all root-to-leaf paths in any order.
 *
 * Example:
 * Input: root = [1,2,3,null,5]
 * Output: ["1->2->5", "1->3"]
 *
 * Approach:
 * - Use DFS and backtracking to build paths from root to each leaf.
 * - At each node, append the node's value to the current path.
 * - When a leaf is reached, add the path to the result list.
 *
 * Time Complexity: O(n^2), where n is the number of nodes (each path can be up to O(n) in length).
 * Space Complexity: O(n^2), for storing all paths.
 */
public class BinaryTreePaths {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root != null) dfs(root, "", result);
        return result;
    }

    private void dfs(TreeNode node, String path, List<String> result) {
        if (node.left == null && node.right == null) {
            result.add(path + node.val);
            return;
        }
        if (node.left != null) dfs(node.left, path + node.val + "->", result);
        if (node.right != null) dfs(node.right, path + node.val + "->", result);
    }

    // Example usage
    public static void main(String[] args) {
        /*
         * Construct the following tree:
         *      1
         *     / \
         *    2   3
         *     \
         *      5
         */
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(5);

        BinaryTreePaths solver = new BinaryTreePaths();
        List<String> paths = solver.binaryTreePaths(root);
        System.out.println("All root-to-leaf paths:");
        for (String path : paths) {
            System.out.println(path);
        }
    }
}
