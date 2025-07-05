package interview.problems.solved;

import java.util.Stack;

/**
 * Problem Description:
 * ---------------------
 * Convert a Binary Search Tree (BST) into a Greater Tree such that every node's value is updated
 * to the sum of all values greater than or equal to the node's value in the original BST.
 *
 * Approach:
 * ---------
 * 1. Use an iterative approach with a stack to perform reverse in-order traversal (right-root-left).
 * 2. Maintain a running sum to update each node's value.
 *
 * Complexity:
 * -----------
 * - Time Complexity: O(n), where n is the number of nodes in the BST.
 * - Space Complexity: O(h), where h is the height of the BST (for the stack).
 */
public class ConvertBSTToGreaterTree {

    /**
     * Function to convert a BST to a Greater Tree using an iterative approach with a stack.
     *
     * @param root The root of the BST.
     * @return The root of the Greater Tree.
     */
    public TreeNode convertBST(TreeNode root) {
        if (root == null) {
            return null;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        int runningSum = 0;

        // Perform reverse in-order traversal using a stack
        while (!stack.isEmpty() || current != null) {
            // Push all right nodes onto the stack
            while (current != null) {
                stack.push(current);
                current = current.right;
            }

            // Process the node at the top of the stack
            current = stack.pop();
            runningSum += current.val; // Update the running sum
            current.val = runningSum; // Update the node's value

            // Move to the left subtree
            current = current.left;
        }

        return root;
    }

    public static void main(String[] args) {
        // Example: Create a BST
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(1);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(2);
        root.left.right.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);

        ConvertBSTToGreaterTree converter = new ConvertBSTToGreaterTree();

        System.out.println("Original BST (In-order): ");
        inOrderTraversal(root); // Output: 0 1 2 3 4 5 6 7
        System.out.println();

        System.out.println();
        DrawBinaryTreeWithSpacing.drawTree(root); // Visualize the tree

        // Convert the BST to a Greater Tree
        converter.convertBST(root);

        System.out.println("Greater Tree (In-order): ");
        inOrderTraversal(root); // Output: 36 36 35 33 30 26 21 15

        System.out.println();
        DrawBinaryTreeWithSpacing.drawTree(root); // Visualize the tree
    }

    /**
     * Helper function to perform in-order traversal of a tree.
     *
     * @param root The root of the tree.
     */
    public static void inOrderTraversal(TreeNode root) {
        if (root != null) {
            inOrderTraversal(root.left);
            System.out.print(root.val + " ");
            inOrderTraversal(root.right);
        }
    }
}

    // Using shared TreeNode class
