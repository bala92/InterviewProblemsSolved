package interview.problems.solved;

/**
 * Problem Description:
 * ---------------------
 * Merge two binary trees into a new binary tree. The merge rule is:
 * 1. If two nodes overlap, sum their values as the new value of the merged node.
 * 2. Otherwise, the non-null node will be used as the node of the new tree.
 *
 * Approach:
 * ---------
 * Use a recursive traversal to merge the two trees:
 * - If both nodes are non-null, sum their values and recursively merge their left and right children.
 * - If one of the nodes is null, return the non-null node.
 *
 * Complexity:
 * -----------
 * - Time Complexity: O(n), where n is the total number of nodes in the smaller tree.
 * - Space Complexity: O(h), where h is the height of the smaller tree (recursion stack).
 */
public class MergeTwoBinaryTrees {

    /**
     * Function to merge two binary trees.
     *
     * @param root1 The root of the first binary tree.
     * @param root2 The root of the second binary tree.
     * @return The root of the merged binary tree.
     */
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        // Base case: If one of the nodes is null, return the other node
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }

        // Merge the values of the two nodes
        root1.val += root2.val;

        // Recursively merge the left and right subtrees
        root1.left = mergeTrees(root1.left, root2.left);
        root1.right = mergeTrees(root1.right, root2.right);

        return root1;
    }

    /**
     * Helper function to print the binary tree in preorder traversal.
     *
     * @param root The root of the binary tree.
     */
    public void printPreorder(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + " ");
        printPreorder(root.left);
        printPreorder(root.right);
    }

    public static void main(String[] args) {
        MergeTwoBinaryTrees solution = new MergeTwoBinaryTrees();

        // Example 1
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(3);
        root1.right = new TreeNode(2);
        root1.left.left = new TreeNode(5);

        TreeNode root2 = new TreeNode(2);
        root2.left = new TreeNode(1);
        root2.right = new TreeNode(3);
        root2.left.right = new TreeNode(4);
        root2.right.right = new TreeNode(7);

        TreeNode mergedRoot = solution.mergeTrees(root1, root2);
        System.out.print("Merged Tree (Preorder Traversal): ");
        solution.printPreorder(mergedRoot);
        // Output: 3 4 5 4 5 7
    }
}

    // Using shared TreeNode class
