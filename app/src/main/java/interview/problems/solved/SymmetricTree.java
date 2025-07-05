package interview.problems.solved;

/**
 * LeetCode 101: Symmetric Tree
 *
 * Problem:
 * Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
 *
 * Example:
 * Input: root = [1,2,2,3,4,4,3]
 * Output: true
 *
 * Approach:
 * - Use recursion to compare the left and right subtrees.
 * - For two nodes to be symmetric:
 *   - Their values must be equal.
 *   - The left subtree of the left node must be a mirror of the right subtree of the right node, and vice versa.
 * - Base cases: both nodes are null (symmetric), one is null (not symmetric), values differ (not symmetric).
 *
 * Time Complexity: O(n), where n is the number of nodes (each node is visited once).
 * Space Complexity: O(h), where h is the height of the tree (recursion stack).
 */
public class SymmetricTree {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        if (t1.val != t2.val) return false;
        return isMirror(t1.left, t2.right) && isMirror(t1.right, t2.left);
    }

    // Example usage
    public static void main(String[] args) {
        /*
         * Construct the following tree:
         *      1
         *     / \
         *    2   2
         *   / \ / \
         *  3  4 4  3
         */
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);

        SymmetricTree solver = new SymmetricTree();
        System.out.println("Is symmetric? " + solver.isSymmetric(root)); // Output: true
    }
}
