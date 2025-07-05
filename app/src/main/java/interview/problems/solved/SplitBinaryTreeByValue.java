package interview.problems.solved;

/**
 * Problem Description:
 * ---------------------
 * Given the root of a binary search tree (BST) and an integer k, split the tree into two subtrees:
 * 1. One subtree contains all nodes with values less than k.
 * 2. The other subtree contains all nodes with values greater than or equal to k.
 *
 * Return the roots of the two subtrees as a pair.
 *
 * Approach:
 * ---------
 * Use a recursive approach:
 * - Traverse the tree recursively.
 * - At each node:
 *   - If the node's value is less than k, it belongs to the "less than k" subtree.
 *   - If the node's value is greater than or equal to k, it belongs to the "greater than or equal to k" subtree.
 * - Reassign subtrees during the traversal.
 *
 * Complexity:
 * -----------
 * - Time Complexity: O(n), where n is the number of nodes in the tree.
 * - Space Complexity: O(h), where h is the height of the tree (recursion stack).
 */
public class SplitBinaryTreeByValue {

    /**
     * Function to split a binary search tree into two subtrees based on a value k.
     *
     * @param root The root of the binary search tree.
     * @param k The value to split the tree.
     * @return An array of two TreeNodes: [lessThanKTree, greaterThanOrEqualToKTree].
     */
    public TreeNode[] splitBST(TreeNode root, int k) {
        if (root == null) {
            return new TreeNode[]{null, null};
        }

        if (root.val == k) {
            // If the current node's value is equal to k, split it into two subtrees
            TreeNode left = root.left;
            root.left = null;
            return new TreeNode[]{left, root};
        } 
        else if (root.val < k) {
            // The current node belongs to the "less than k" subtree
            TreeNode[] split = splitBST(root.right, k);
            root.right = split[0]; // Reassign the right child
            return new TreeNode[]{root, split[1]};
        } 
        else {
            // The current node belongs to the "greater than or equal to k" subtree
            TreeNode[] split = splitBST(root.left, k);
            root.left = split[1]; // Reassign the left child
            return new TreeNode[]{split[0], root};
        }
    }

    public static void main(String[] args) {
        SplitBinaryTreeByValue solution = new SplitBinaryTreeByValue();

        // Example 1
        TreeNode root1 = new TreeNode(4);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(6);
        root1.left.left = new TreeNode(1);
        root1.left.right = new TreeNode(3);
        root1.right.left = new TreeNode(5);
        root1.right.right = new TreeNode(7);

        int k1 = 3;
        TreeNode[] result1 = solution.splitBST(root1, k1);
        System.out.println("Less than " + k1 + ":");
        //printTree(result1[0]);
        DrawBinaryTreeWithSpacing.drawTree(result1[0]);
        System.out.println("Greater than or equal to " + k1 + ":");
        //printTree(result1[1]);
        DrawBinaryTreeWithSpacing.drawTree(result1[1]);
        // Output:
        // Less than 3:
        // 2
        // Greater than or equal to 3:
        // 4

        // Example 2
        TreeNode root2 = new TreeNode(1);
        int k2 = 2;
        TreeNode[] result2 = solution.splitBST(root2, k2);
        System.out.println("Less than " + k2 + ":");
        printTree(result2[0]);
        System.out.println("Greater than or equal to " + k2 + ":");
        printTree(result2[1]);
        // Output:
        // Less than 2:
        // 1
        // Greater than or equal to 2:
        // null
    }

    /**
     * Helper function to print a binary tree in preorder traversal.
     *
     * @param root The root of the binary tree.
     */
    private static void printTree(TreeNode root) {
        if (root == null) {
            System.out.println("null");
            return;
        }
        System.out.print(root.val + " ");
        printTree(root.left);
        printTree(root.right);
    }
}

    // Using shared TreeNode class
