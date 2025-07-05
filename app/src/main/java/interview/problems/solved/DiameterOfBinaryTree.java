package interview.problems.solved;

/**
 * LeetCode 543: Diameter of Binary Tree
 *
 * Problem:
 * Given the root of a binary tree, return the length of the diameter of the tree.
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
 * This path may or may not pass through the root.
 *
 * Example:
 * Input: root = [1,2,3,4,5]
 * Output: 3
 * Explanation: The longest path is [4,2,1,3] or [5,2,1,3], and its length is 3.
 *
 * Approach:
 * - Use DFS to compute the depth of each subtree.
 * - At each node, the diameter passing through it is leftDepth + rightDepth.
 * - Track the maximum diameter found during traversal.
 *
 * Time Complexity: O(n), where n is the number of nodes (each node is visited once).
 * Space Complexity: O(h), where h is the height of the tree (recursion stack).
 */
public class DiameterOfBinaryTree {

    private int maxDiameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        depth(root);
        return maxDiameter;
    }

    private int depth(TreeNode node) {
        if (node == null) return 0;
        int left = depth(node.left);
        int right = depth(node.right);
        maxDiameter = Math.max(maxDiameter, left + right);
        return 1 + Math.max(left, right);
    }

    // Example usage
    public static void main(String[] args) {
        /*
         * Construct the following tree:
         *      1
         *     / \
         *    2   3
         *   / \
         *  4   5
         */
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        DiameterOfBinaryTree solver = new DiameterOfBinaryTree();
        int diameter = solver.diameterOfBinaryTree(root);
        System.out.println("Diameter of the binary tree: " + diameter); // Output: 3
    }
}
