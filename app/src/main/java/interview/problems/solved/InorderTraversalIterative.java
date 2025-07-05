package interview.problems.solved;

import java.util.*;

/**
 * LeetCode 94: Binary Tree Inorder Traversal (Iterative)
 *
 * Problem:
 * Given the root of a binary tree, return the inorder traversal of its nodes' values.
 *
 * Example:
 * Input: root = [1,null,2,3]
 * Output: [1,3,2]
 *
 * Approach:
 * - Use a stack to simulate the recursion for inorder traversal.
 * - Start from the root, push all left children onto the stack.
 * - Pop from the stack, visit the node, then move to its right child.
 * - Repeat until both the stack is empty and the current node is null.
 *
 * Time Complexity: O(n), where n is the number of nodes (each node is visited once).
 * Space Complexity: O(h), where h is the height of the tree (stack size).
 */
public class InorderTraversalIterative {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            result.add(curr.val);
            curr = curr.right;
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

        InorderTraversalIterative solver = new InorderTraversalIterative();
        List<Integer> inorder = solver.inorderTraversal(root);
        System.out.println("Inorder traversal: " + inorder); // Output: [1, 3, 2]
    }
}
