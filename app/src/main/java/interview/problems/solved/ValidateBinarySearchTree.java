package interview.problems.solved;

/**
 * LeetCode 98: Validate Binary Search Tree
 *
 * Problem:
 * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
 * A valid BST is defined as:
 *   - The left subtree of a node contains only nodes with keys less than the node's key.
 *   - The right subtree of a node contains only nodes with keys greater than the node's key.
 *   - Both the left and right subtrees must also be binary search trees.
 *
 * Example:
 * Input: root = [2,1,3]
 * Output: true
 *
 * Approach:
 * - Use recursion to check that every node's value is within a valid range (min, max).
 * - For the left child, the max is the current node's value.
 * - For the right child, the min is the current node's value.
 * - If any node violates the BST property, return false.
 *
 * Time Complexity: O(n), where n is the number of nodes (each node is visited once).
 * Space Complexity: O(h), where h is the height of the tree (recursion stack).
 */
public class ValidateBinarySearchTree {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public boolean isValidBST(TreeNode root) {
        return validate(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean validate(TreeNode node, int min, int max) {
        if (node == null) return true;
        if (node.val <= min || node.val >= max) return false;
        return validate(node.left, min, node.val) && validate(node.right, node.val, max);
    }

    // Example usage
    public static void main(String[] args) {
        /*
         * Construct the following tree:
         *      2
         *     / \
         *    1   3
         */
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);

        ValidateBinarySearchTree solver = new ValidateBinarySearchTree();
        System.out.println("Is valid BST? " + solver.isValidBST(root)); // Output: true

        /*
         * Invalid BST:
         *      5
         *     / \
         *    1   4
         *       / \
         *      3   6
         */
        TreeNode root2 = new TreeNode(5);
        root2.left = new TreeNode(1);
        root2.right = new TreeNode(4);
        root2.right.left = new TreeNode(3);
        root2.right.right = new TreeNode(6);
        System.out.println("Is valid BST? " + solver.isValidBST(root2)); // Output: false
    }
}
