package interview.problems.solved;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Problem Description:
 * ---------------------
 * Draw a binary tree downwards in a structured format.
 *
 * Approach:
 * ---------
 * 1. Use recursion to traverse the tree and build each level of the tree.
 * 2. Print the tree level by level with appropriate spacing and branches.
 *
 * Complexity:
 * -----------
 * - Time Complexity: O(n), where n is the number of nodes in the tree.
 * - Space Complexity: O(h), where h is the height of the tree (for the recursion stack).
 */
public class DrawBinaryTreeDownwards {

    /**
     * Function to draw a binary tree downwards.
     *
     * @param root The root of the binary tree.
     */
    public static void drawTree(TreeNode root) {
        if (root == null) {
            System.out.println("The tree is empty.");
            return;
        }

        List<List<String>> lines = new ArrayList<>();
        int height = getHeight(root);
        int width = (int) Math.pow(2, height) - 1;

        // Build the tree representation
        buildTreeLines(root, 0, 0, width - 1, lines, height);

        // Print the tree
        for (List<String> line : lines) {
            System.out.println(String.join("", line));
        }
    }

    /**
     * Helper function to build the tree lines for printing.
     *
     * @param node The current node.
     * @param level The current level of the tree.
     * @param left The left boundary of the current node.
     * @param right The right boundary of the current node.
     * @param lines The list of lines representing the tree.
     * @param height The height of the tree.
     */
    private static void buildTreeLines(TreeNode node, int level, int left, int right, List<List<String>> lines, int height) {
        if (node == null || left > right) {
            return;
        }

        // Ensure the lines list has enough levels
        while (lines.size() <= level) {
            lines.add(new ArrayList<>(Collections.nCopies((int) Math.pow(2, height) - 1, " ")));
        }

        int mid = (left + right) / 2;
        lines.get(level).set(mid, String.valueOf(node.val));

        // Add branches for the next level
        if (level + 1 < height) {
            if (node.left != null && mid - 1 >= 0) {
                lines.get(level + 1).set(mid - 1, "/");
            }
            if (node.right != null && mid + 1 < lines.get(level + 1).size()) {
                lines.get(level + 1).set(mid + 1, "\\");
            }
        }

        // Recursively build the left and right subtrees
        buildTreeLines(node.left, level + 2, left, mid - 1, lines, height);
        buildTreeLines(node.right, level + 2, mid + 1, right, lines, height);
    }

    /**
     * Helper function to calculate the height of the tree.
     *
     * @param root The root of the binary tree.
     * @return The height of the tree.
     */
    private static int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }

    public static void main(String[] args) {
        // Example: Create a binary tree
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);

        System.out.println("Binary Tree (Downwards):");
        drawTree(root);
    }
}

    // Using shared TreeNode class
