package interview.problems.solved;

/**
 * LeetCode 110: Balanced Binary Tree
 *
 * Given a binary tree, determine if it is height-balanced.
 * A binary tree is balanced if the depths of the two subtrees of every node never differ by more than 1.
 *
 * Approach:
 * - Use recursion to check the height of each subtree.
 * - If any subtree is unbalanced, propagate -1 upwards.
 * - If the height difference between left and right is more than 1, return -1 (unbalanced).
 * - Otherwise, return the height of the subtree.
 * - Time Complexity: O(n), where n is the number of nodes in the tree.
 */
public class BalancedBinaryTree {
    // Definition for a binary tree node.
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { val = x; }
    }

    public boolean isBalanced(TreeNode root) {
        return checkHeight(root) != -1;
    }

    // Returns the height if balanced, -1 if not balanced
    private int checkHeight(TreeNode node) {
        if (node == null) return 0;
        int left = checkHeight(node.left);
        if (left == -1) return -1;
        int right = checkHeight(node.right);
        if (right == -1) return -1;
        if (Math.abs(left - right) > 1) return -1;
        return 1 + Math.max(left, right);
    }

    // Example usage
    public static void main(String[] args) {
        /*
         * Example tree:
         *      3
         *     / \
         *    9  20
         *       /  \
         *      15   7
         *
         * Output: true
         */
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        BalancedBinaryTree solver = new BalancedBinaryTree();
        boolean result = solver.isBalanced(root);
        System.out.println("Is balanced: " + result); // Output: true
    }
}
