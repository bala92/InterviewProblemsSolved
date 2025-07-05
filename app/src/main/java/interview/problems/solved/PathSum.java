package interview.problems.solved;

/**
 * LeetCode 112: Path Sum
 *
 * Problem:
 * Given the root of a binary tree and an integer targetSum, return true if the tree has a root-to-leaf path
 * such that adding up all the values along the path equals targetSum.
 *
 * Example:
 * Input: root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
 * Output: true
 * Explanation: The path 5 -> 4 -> 11 -> 2 sums to 22.
 *
 * Approach:
 * - Use DFS to traverse the tree from root to leaf.
 * - At each node, subtract the node's value from targetSum.
 * - If a leaf node is reached and the remaining sum is 0, return true.
 * - Otherwise, recursively check left and right subtrees.
 *
 * Time Complexity: O(n), where n is the number of nodes (each node is visited once).
 * Space Complexity: O(h), where h is the height of the tree (recursion stack).
 */
public class PathSum {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        // If it's a leaf node, check if the remaining sum equals the node's value
        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }
        // Otherwise, check left and right subtrees
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

    // Example usage
    public static void main(String[] args) {
        /*
         * Construct the following tree:
         *      5
         *     / \
         *    4   8
         *   /   / \
         * 11  13  4
         * / \       \
         *7   2       1
         */
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(1);

        PathSum solver = new PathSum();
        int targetSum = 22;
        System.out.println("Has path sum 22? " + solver.hasPathSum(root, targetSum)); // Output: true
    }
}
