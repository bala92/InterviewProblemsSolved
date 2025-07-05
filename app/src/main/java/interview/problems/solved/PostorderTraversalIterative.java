package interview.problems.solved;

import java.util.*;

/**
 * LeetCode 145: Binary Tree Postorder Traversal (Recursive)
 *
 * Problem:
 * Given the root of a binary tree, return the postorder traversal of its nodes' values.
 *
 * Example:
 * Input: root = [1,null,2,3]
 * Output: [3,2,1]
 *
 * Approach:
 * - Use recursion to traverse the left and right subtrees before processing the root.
 * - Add the value of the node to the result list after visiting its children.
 *
 * Time Complexity: O(n), where n is the number of nodes (each node is visited once).
 * Space Complexity: O(h), where h is the height of the tree (due to recursion stack).
 */
public class PostorderTraversalIterative {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postorderHelper(root, result);
        return result;
    }

    private void postorderHelper(TreeNode node, List<Integer> result) {
        if (node == null) return;
        postorderHelper(node.left, result);
        postorderHelper(node.right, result);
        result.add(node.val);
    }

    // Example usage
    public static void main(String[] args) {
        /*
         * Construct the following tree:
         *      1
         *       \
         *        2
         *       /
         *      3
         */
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        PostorderTraversalIterative solver = new PostorderTraversalIterative();
        List<Integer> postorder = solver.postorderTraversal(root);
        System.out.println("Postorder traversal: " + postorder); // Output: [3, 2, 1]
    }
}
