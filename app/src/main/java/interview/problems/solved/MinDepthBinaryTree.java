package interview.problems.solved;

/**
 * LeetCode 111: Minimum Depth of Binary Tree
 *
 * Problem:
 * Given the root of a binary tree, return its minimum depth.
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 *
 * Example:
 * Input: root = [3,9,20,null,null,15,7]
 * Output: 2
 * Explanation: The minimum depth is 2 (3 -> 9).
 *
 * Approach:
 * - Use recursion to find the minimum depth of left and right subtrees.
 * - If a node has only one child, consider only the non-null child for the minimum depth.
 * - The minimum depth of a leaf node is 1.
 *
 * Time Complexity: O(n), where n is the number of nodes (each node is visited once).
 * Space Complexity: O(h), where h is the height of the tree (recursion stack).
 */
public class MinDepthBinaryTree {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        if (root.left == null) return 1 + minDepth(root.right);
        if (root.right == null) return 1 + minDepth(root.left);
        return 1 + Math.min(minDepth(root.left), minDepth(root.right));
    }

    // Example usage
    public static void main(String[] args) {
        /*
         * Construct the following tree:
         *      3
         *     / \
         *    9  20
         *       /  \
         *      15   7
         */
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        MinDepthBinaryTree solver = new MinDepthBinaryTree();
        System.out.println("Minimum depth: " + solver.minDepth(root)); // Output: 2
    }
}
