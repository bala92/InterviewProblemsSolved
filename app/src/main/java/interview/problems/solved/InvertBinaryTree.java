package interview.problems.solved;

/**
 * LeetCode 226: Invert Binary Tree
 *
 * Problem:
 * Given the root of a binary tree, invert the tree, and return its root.
 *
 * Example:
 * Input: root = [4,2,7,1,3,6,9]
 * Output: [4,7,2,9,6,3,1]
 *
 * Approach:
 * - Use recursion to swap the left and right children of every node in the tree.
 * - For each node, recursively invert its left and right subtrees, then swap them.
 *
 * Time Complexity: O(n), where n is the number of nodes (each node is visited once).
 * Space Complexity: O(h), where h is the height of the tree (recursion stack).
 */
public class InvertBinaryTree {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
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
         * Construct the following tree:
         *      4
         *     / \
         *    2   7
         *   / \ / \
         *  1  3 6  9
         */
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);

        InvertBinaryTree solver = new InvertBinaryTree();
        System.out.print("Original tree preorder: ");
        solver.printPreorder(root);
        solver.invertTree(root);
        System.out.print("\nInverted tree preorder: ");
        solver.printPreorder(root);
        // Output: Original tree preorder: 4 2 1 3 7 6 9
        //         Inverted tree preorder: 4 7 9 6 2 3 1
    }
}
