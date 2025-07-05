package interview.problems.solved;

/**
 * LeetCode 669: Trim a Binary Search Tree
 *
 * Problem:
 * Given the root of a binary search tree and the lowest and highest boundaries as low and high,
 * trim the tree so that all its elements lies in [low, high] (inclusive).
 * You might need to change the root of the tree, so the result should return the new root of the trimmed binary search tree.
 *
 * Example:
 * Input: root = [1,0,2], low = 1, high = 2
 * Output: [1,null,2]
 *
 * Approach:
 * - Use recursion to traverse the tree.
 * - If the current node's value is less than low, trim the left subtree and return the trimmed right subtree.
 * - If the current node's value is greater than high, trim the right subtree and return the trimmed left subtree.
 * - Otherwise, recursively trim both left and right subtrees.
 *
 * Time Complexity: O(n), where n is the number of nodes (each node is visited once).
 * Space Complexity: O(h), where h is the height of the tree (recursion stack).
 */
public class TrimBST {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) return null;
        if (root.val < low) return trimBST(root.right, low, high);
        if (root.val > high) return trimBST(root.left, low, high);
        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);
        return root;
    }

    // Helper to print tree in preorder
    public void printPreorder(TreeNode root) {
        if (root == null) return;
        System.out.print(root.val + " ");
        printPreorder(root.left);
        printPreorder(root.right);
    }

    // Example usage
    public static void main(String[] args) {
        /*
         * Construct the following BST:
         *      3
         *     / \
         *    0   4
         *     \
         *      2
         *     /
         *    1
         */
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(0);
        root.right = new TreeNode(4);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(1);

        TrimBST solver = new TrimBST();
        int low = 1, high = 3;
        TreeNode trimmed = solver.trimBST(root, low, high);
        System.out.print("Trimmed BST preorder: ");
        solver.printPreorder(trimmed); // Output: 3 2 1
    }
}
