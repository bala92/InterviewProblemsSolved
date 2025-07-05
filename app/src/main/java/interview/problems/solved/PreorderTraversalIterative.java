package interview.problems.solved;

import java.util.*;

/**
 * LeetCode 144: Binary Tree Preorder Traversal (Iterative)
 *
 * Problem:
 * Given the root of a binary tree, return the preorder traversal of its nodes' values.
 *
 * Example:
 * Input: root = [1,null,2,3]
 * Output: [1,2,3]
 *
 * Approach:
 * - Use a stack to simulate the recursion for preorder traversal.
 * - Start from the root, push it onto the stack.
 * - Pop from the stack, visit the node, then push its right and left children (right first so left is processed first).
 * - Repeat until the stack is empty.
 *
 * Time Complexity: O(n), where n is the number of nodes (each node is visited once).
 * Space Complexity: O(h), where h is the height of the tree (stack size).
 */
public class PreorderTraversalIterative {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.val);
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }
        return result;
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

        PreorderTraversalIterative solver = new PreorderTraversalIterative();
        List<Integer> preorder = solver.preorderTraversal(root);
        System.out.println("Preorder traversal: " + preorder); // Output: [1, 2, 3]
    }
}
