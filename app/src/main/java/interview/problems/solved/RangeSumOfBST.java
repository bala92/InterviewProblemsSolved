package interview.problems.solved;

public class RangeSumOfBST {

    /**
     * Calculates the range sum of BST using recursive DFS.
     *
     * @param root The root of the BST.
     * @param low  The lower bound of the range.
     * @param high The upper bound of the range.
     * @return The sum of all node values within the range [low, high].
     */
    public static int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) {
            return 0;
        }

        int sum = 0;

        // If the current node is within the range, add its value to the sum
        if (root.val >= low && root.val <= high) {
            sum += root.val;
            sum += rangeSumBST(root.right, low, high);
            sum += rangeSumBST(root.left, low, high);
        }

        // If the current node's value is greater than low, explore the left subtree
        if (root.val < low) {
            sum += rangeSumBST(root.right, low, high);
        }

        // If the current node's value is less than high, explore the right subtree
        if (root.val > high) {
            sum += rangeSumBST(root.left, low, high);
        }

        return sum;
    }

    public static void main(String[] args) {
        // Example usage
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(7);
        root.right.right = new TreeNode(18);

        DrawBinaryTreeWithSpacing.drawTree(root);

        int low = 7, high = 15;
        int result = rangeSumBST(root, low, high);
        System.out.println("Range Sum of BST: " + result); // Output: 32
    }
}

    // Using shared TreeNode class
