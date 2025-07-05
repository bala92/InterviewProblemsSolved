package interview.problems.solved;

/**
 * LeetCode 1008: Construct Binary Search Tree from Preorder Traversal
 *
 * Problem:
 * Given an array of integers preorder, which represents the preorder traversal of a BST (Binary Search Tree),
 * construct the tree and return its root.
 *
 * Example:
 * Input: preorder = [8,5,1,7,10,12]
 * Output: [8,5,10,1,7,null,12]
 *
 * Approach:
 * - Use recursion with bounds to construct the BST efficiently.
 * - Maintain an index to track the current node in preorder.
 * - For each node, ensure its value is within the valid range (min, max) for a BST.
 * - Recursively build the left and right subtrees.
 *
 * Time Complexity: O(n), where n is the number of nodes (each node is visited once).
 * Space Complexity: O(n) for the recursion stack in the worst case (skewed tree).
 */
public class ConstructBSTFromPreorder {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    private int idx = 0;

    public TreeNode bstFromPreorder(int[] preorder) {
        return helper(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private TreeNode helper(int[] preorder, int min, int max) {
        if (idx == preorder.length) return null;
        int val = preorder[idx];
        if (val < min || val > max) return null;
        idx++;
        TreeNode root = new TreeNode(val);
        root.left = helper(preorder, min, val - 1);
        root.right = helper(preorder, val + 1, max);
        return root;
    }

    // Utility to print inorder traversal
    private void printInorder(TreeNode root) {
        if (root == null) return;
        printInorder(root.left);
        System.out.print(root.val + " ");
        printInorder(root.right);
    }

    // Example usage
    public static void main(String[] args) {
        ConstructBSTFromPreorder solver = new ConstructBSTFromPreorder();
        int[] preorder = {8, 5, 1, 7, 10, 12};
        TreeNode root = solver.bstFromPreorder(preorder);
        System.out.print("Inorder traversal of constructed BST: ");
        solver.printInorder(root); // Output should be sorted: 1 5 7 8 10 12
    }
}
