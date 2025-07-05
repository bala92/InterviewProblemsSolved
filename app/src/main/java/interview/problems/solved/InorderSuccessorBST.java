package interview.problems.solved;

/**
 * LeetCode 285: Inorder Successor in BST
 *
 * Problem:
 * Given the root of a Binary Search Tree and a node p, find the inorder successor of that node in the BST.
 * The successor of a node p is the node with the smallest key greater than p.val.
 *
 * Approach:
 * ---------
 * 1. Start from the root and search for the successor:
 *    - If root.val > p.val, root could be a successor. Move left to find a smaller one.
 *    - If root.val <= p.val, move right.
 * 2. The last recorded node with root.val > p.val is the answer.
 *
 * Time Complexity: O(h), where h is the height of the tree.
 * Space Complexity: O(1).
 */
public class InorderSuccessorBST {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    /**
     * Finds the inorder successor of node p in the BST rooted at root.
     * @param root The root of the BST.
     * @param p The node whose successor is to be found.
     * @return The inorder successor node, or null if none exists.
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode successor = null;
        while (root != null) {
            if (p.val < root.val) {
                successor = root;
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return successor;
    }

    // Example usage
    public static void main(String[] args) {
        /*
         * Construct the following BST:
         *      5
         *     / \
         *    3   6
         *   / \
         *  2   4
         * /
         *1
         */
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.left.left.left = new TreeNode(1);

        InorderSuccessorBST solution = new InorderSuccessorBST();
        TreeNode p = root.left.right; // Node with value 4
        TreeNode successor = solution.inorderSuccessor(root, p);
        System.out.println("Inorder successor of " + p.val + " is: " + (successor != null ? successor.val : "null"));
        // Output: Inorder successor of 4 is: 5
    }
}
