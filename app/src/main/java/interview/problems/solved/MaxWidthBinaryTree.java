package interview.problems.solved;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Problem Description:
 * ---------------------
 * Given the root of a binary tree, return the maximum width of the tree.
 * The width of a level is defined as the number of nodes between the leftmost
 * and rightmost non-null nodes in that level, including null nodes in between.
 *
 * Approach:
 * ---------
 * Use level-order traversal (BFS) with indexing:
 * - Assign an index to each node as if the tree were a complete binary tree.
 * - For each level, calculate the width as:
 *   width = last_index - first_index + 1
 * - Track the maximum width across all levels.
 *
 * Complexity:
 * -----------
 * - Time Complexity: O(n), where n is the number of nodes in the tree.
 * - Space Complexity: O(n), for the queue used in level-order traversal.
 */
public class MaxWidthBinaryTree {

    /**
     * Function to find the maximum width of a binary tree.
     *
     * @param root The root of the binary tree.
     * @return The maximum width of the binary tree.
     */
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // Queue to store nodes along with their indices
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        queue.offer(new Pair<>(root, 0)); // Start with the root node at index 0

        int maxWidth = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            int firstIndex = queue.peek().index; // Index of the first node in the level
            int lastIndex = firstIndex;         // Initialize lastIndex

            for (int i = 0; i < size; i++) {
                Pair<TreeNode, Integer> current = queue.poll();
                TreeNode node = current.node;
                int index = current.index;

                // Update lastIndex
                lastIndex = index;

                // Add left and right children to the queue with their respective indices
                if (node.left != null) {
                    queue.offer(new Pair<>(node.left, 2 * index));
                }
                if (node.right != null) {
                    queue.offer(new Pair<>(node.right, 2 * index + 1));
                }
            }

            // Calculate the width of the current level
            maxWidth = Math.max(maxWidth, lastIndex - firstIndex + 1);
        }

        return maxWidth;
    }

    public static void main(String[] args) {
        MaxWidthBinaryTree solution = new MaxWidthBinaryTree();

        // Example 1
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(3);
        root1.right = new TreeNode(2);
        root1.left.left = new TreeNode(5);
        root1.left.right = new TreeNode(3);
        root1.right.right = new TreeNode(9);
        System.out.println("Maximum Width (Example 1): " + solution.widthOfBinaryTree(root1));
        // Output: 4

        // Example 2
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(3);
        root2.left.left = new TreeNode(5);
        root2.left.right = new TreeNode(3);
        System.out.println("Maximum Width (Example 2): " + solution.widthOfBinaryTree(root2));
        // Output: 2

        // Example 3
        TreeNode root3 = new TreeNode(1);
        root3.left = new TreeNode(3);
        root3.right = new TreeNode(2);
        root3.left.left = new TreeNode(5);
        System.out.println("Maximum Width (Example 3): " + solution.widthOfBinaryTree(root3));
        // Output: 2
    }
}

/**
 * Helper class to store a node and its index.
 */
class Pair<T, U> {
    public T node;
    public U index;

    public Pair(T node, U index) {
        this.node = node;
        this.index = index;
    }
}

    // Using shared TreeNode class
