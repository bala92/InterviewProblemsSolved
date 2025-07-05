package interview.problems.solved;

/**
 * LeetCode 235: Lowest Common Ancestor of a Binary Search Tree
 *
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
 * The LCA is defined as the lowest node in the tree that has both p and q as descendants (where a node can be a descendant of itself).
 *
 * Approach:
 * - Start from the root and traverse the tree.
 * - If both p and q are less than root, LCA is in the left subtree.
 * - If both p and q are greater than root, LCA is in the right subtree.
 * - Otherwise, root is the LCA.
 * - Time Complexity: O(h), where h is the height of the tree.
 */
public class LowestCommonAncestorBST {
    // Definition for a binary tree node.
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { val = x; }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null) {
            if (p.val < root.val && q.val < root.val) {
                root = root.left;
            } else if (p.val > root.val && q.val > root.val) {
                root = root.right;
            } else {
                return root;
            }
        }
        return null;
    }

    // Example usage
    public static void main(String[] args) {
        /*
         * Example BST:
         *      6
         *     / \
         *    2   8
         *   / \ / \
         *  0  4 7  9
         *    / \
         *   3   5
         *
         * p = 2, q = 8
         * Output: 6
         */
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(2);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(4);
        root.left.right.left = new TreeNode(3);
        root.left.right.right = new TreeNode(5);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);

        TreeNode p = root.left; // 2
        TreeNode q = root.right; // 8

        LowestCommonAncestorBST solver = new LowestCommonAncestorBST();
        TreeNode lca = solver.lowestCommonAncestor(root, p, q);
        System.out.println("LCA: " + (lca != null ? lca.val : "null")); // Output: 6
    }
}
