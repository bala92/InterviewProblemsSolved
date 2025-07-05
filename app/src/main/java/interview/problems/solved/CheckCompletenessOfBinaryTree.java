package interview.problems.solved;

import java.util.LinkedList;
import java.util.Queue;

/**
 * LeetCode 958: Check Completeness of a Binary Tree
 *
 * Problem:
 * Given the root of a binary tree, determine if it is a complete binary tree.
 * A complete binary tree is a binary tree in which every level, except possibly the last, is completely filled,
 * and all nodes are as far left as possible.
 *
 * Example:
 * Input: root = [1,2,3,4,5,6]
 * Output: true
 *
 * Input: root = [1,2,3,4,5,null,7]
 * Output: false
 *
 * Approach:
 * - Use level order traversal (BFS) with a queue.
 * - Once a null child is seen, all following nodes must be null for the tree to be complete.
 * - If a non-null node is found after a null, the tree is not complete.
 *
 * Time Complexity: O(n), where n is the number of nodes (each node is visited once).
 * Space Complexity: O(n), for the queue.
 */
public class CheckCompletenessOfBinaryTree {

    public boolean isCompleteTree(TreeNode root) {
        if (root == null) return true;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean seenNull = false;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                seenNull = true;
            } else {
                if (seenNull) return false;
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }
        return true;
    }

    // Example usage
    public static void main(String[] args) {
        /*
         * Construct the following tree:
         *      1
         *     / \
         *    2   3
         *   / \ /
         *  4  5 6
         */
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(4);
        root1.left.right = new TreeNode(5);
        root1.right.left = new TreeNode(6);
        System.out.println("Is tree 1 complete? " + new CheckCompletenessOfBinaryTree().isCompleteTree(root1)); // true

        /*
         * Construct the following tree:
         *      1
         *     / \
         *    2   3
         *   / \   \
         *  4  5    7
         */
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.left.left = new TreeNode(4);
        root2.left.right = new TreeNode(5);
        root2.right.right = new TreeNode(7);
        System.out.println("Is tree 2 complete? " + new CheckCompletenessOfBinaryTree().isCompleteTree(root2)); // false
    }
}
