package interview.problems.solved;

/**
 * Problem Description:
 * ---------------------
 * Delete a node from a Binary Search Tree (BST) while maintaining the BST property.
 *
 * Approach:
 * ---------
 * 1. Traverse the tree to locate the node with the given key.
 * 2. Handle three cases:
 *    - Case 1: The node is a leaf node (no children). Remove it directly.
 *    - Case 2: The node has one child. Replace the node with its child.
 *    - Case 3: The node has two children. Replace the node with its in-order successor
 *      (the smallest node in the right subtree) or in-order predecessor (the largest node in the left subtree).
 * 3. Return the root of the modified tree.
 *
 * Complexity:
 * -----------
 * - Time Complexity: O(h), where h is the height of the tree.
 *   - In the worst case, h = O(n) for a skewed tree.
 *   - In the best case, h = O(log n) for a balanced tree.
 * - Space Complexity: O(h), for the recursion stack.
 */
public class DeleteBSTNode {

    /**
     * Function to delete a node from a BST.
     *
     * @param root The root of the BST.
     * @param key  The value of the node to delete.
     * @return The root of the modified BST.
     */
    public static TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null; // Base case: If the tree is empty
        }

        // Step 1: Locate the node to delete
        if (key < root.val) {
            root.left = deleteNode(root.left, key); // Recur on the left subtree
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key); // Recur on the right subtree
        } else {
            // Step 2: Handle the three cases for deletion

            // Case 1: Node is a leaf node (no children)
            if (root.left == null && root.right == null) {
                return null;
            }

            // Case 2: Node has one child
            if (root.left == null) {
                return root.right; // Replace with the right child
            } else if (root.right == null) {
                return root.left; // Replace with the left child
            }

            // Case 3: Node has two children
            // Find the in-order successor (smallest node in the right subtree)
            TreeNode successor = findMin(root.right);
            root.val = successor.val; // Replace the node's value with the successor's value
            root.right = deleteNode(root.right, successor.val); // Delete the successor
        }

        return root; // Return the root of the modified tree
    }

    /**
     * Helper function to find the smallest node in a subtree.
     *
     * @param node The root of the subtree.
     * @return The smallest node in the subtree.
     */
    private static TreeNode findMin(TreeNode node) {
        while (node.left != null) {
            node = node.left; // Keep going left to find the smallest node
        }
        return node;
    }

    public static void main(String[] args) {
        // Example: Create a BST
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(7);

        System.out.println("Original Tree (In-order): ");
        inOrderTraversal(root); // Output: 2 3 4 5 6 7
        System.out.println();

        // Delete a node with value 3
        root = deleteNode(root, 3);
        System.out.println("Tree after deleting 3 (In-order): ");
        inOrderTraversal(root); // Output: 2 4 5 6 7
        System.out.println();

        // Delete a node with value 5
        root = deleteNode(root, 5);
        System.out.println("Tree after deleting 5 (In-order): ");
        inOrderTraversal(root); // Output: 2 4 6 7
    }

    /**
     * Helper function to perform in-order traversal of a BST.
     *
     * @param root The root of the BST.
     */
    private static void inOrderTraversal(TreeNode root) {
        if (root != null) {
            inOrderTraversal(root.left);
            System.out.print(root.val + " ");
            inOrderTraversal(root.right);
        }
    }
}