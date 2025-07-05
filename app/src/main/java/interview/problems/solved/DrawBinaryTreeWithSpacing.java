package interview.problems.solved;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Problem Description:
 * ---------------------
 * Draw a binary tree with proper spacing in a structured format.
 *
 * Approach:
 * ---------
 * 1. Use level-order traversal (BFS) to traverse the tree level by level.
 * 2. Dynamically calculate the spacing for each level based on the height of the tree.
 * 3. Print each level of the tree with appropriate spacing.
 *
 * Complexity:
 * -----------
 * - Time Complexity: O(n), where n is the number of nodes in the tree.
 * - Space Complexity: O(n), for the queue used in level-order traversal.
 */
public class DrawBinaryTreeWithSpacing {

    /**
     * Function to draw a binary tree with proper spacing.
     *
     * @param root The root of the binary tree.
     */
    public static void drawTree(TreeNode root) {
        if (root == null) {
            System.out.println("The tree is empty.");
            return;
        }

        int height = getHeight(root); // Calculate the height of the tree
        int maxWidth = (int) Math.pow(2, height) - 1; // Maximum width of the tree

        // Use a queue for level-order traversal
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        for (int level = 0; level < height; level++) {
            int levelSize = queue.size(); // Number of nodes at the current level
            int spacing = maxWidth / (levelSize + 1); // Calculate spacing for the current level

            // Print the current level
            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();

                // Print spacing before the node
                System.out.print(" ".repeat(spacing));

                // Print the current node's value or " " for null nodes
                if (current != null) {
                    System.out.print(current.val);
                    queue.add(current.left);  // Add left child to the queue
                    queue.add(current.right); // Add right child to the queue
                } else {
                    System.out.print(" ");
                    queue.add(null); // Add placeholders for null children
                    queue.add(null);
                }

                // Print spacing after the node
                System.out.print(" ".repeat(spacing));
            }

            System.out.println(); // Move to the next level
        }
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

        System.out.println("Binary Tree with Spacing:");
        drawTree(root);
    }
}