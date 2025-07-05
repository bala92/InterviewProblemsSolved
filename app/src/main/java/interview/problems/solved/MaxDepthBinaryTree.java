package interview.problems.solved;

/**
 * LeetCode 104: Maximum Depth of Binary Tree
 *
 * Given the root of a binary tree, return its maximum depth.
 * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 *
 * Approach:
 * - Use recursion: For each node, the max depth is 1 + the maximum of the depths of its left and right subtrees.
 * - Base case: If the node is null, return 0.
 * - Time Complexity: O(n), where n is the number of nodes in the tree.
 */
public class MaxDepthBinaryTree {
    // Definition for a binary tree node.
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { val = x; }
    }

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        return 1 + Math.max(leftDepth, rightDepth);
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
         * Output: 3
         */
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        MaxDepthBinaryTree solver = new MaxDepthBinaryTree();
        int result = solver.maxDepth(root);
        System.out.println("Max depth: " + result); // Output: 3
    }
}
